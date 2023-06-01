using Microsoft.AspNetCore.Diagnostics;
using System.Diagnostics;
using System.Net;
using System.Reflection;
using System.Runtime.CompilerServices;
using System.Text;

namespace ProjektZPO.Services
{
    public static class ExceptionHandlerMiddleware
    {
        private static string GetRealMethodFromAsyncMethod(MethodBase asyncMethod)
        {
            var generatedType = asyncMethod.DeclaringType;
            var originalType = generatedType.DeclaringType;
            var matchingMethods =
                from methodInfo in originalType.GetMethods()
                let attr = methodInfo.GetCustomAttribute<AsyncStateMachineAttribute>()
                where attr != null && attr.StateMachineType == generatedType
                select methodInfo;

            var foundMethod = matchingMethods.Single().Name;
            return foundMethod;
        }
        public static string getErrorDetail(Exception ex)
        {
            var stackTree = new StackTrace(ex, true);
            var frame = stackTree.GetFrame(0);
            var error = new StringBuilder();

            error.Append($"Metoda: {GetRealMethodFromAsyncMethod(frame.GetMethod())}");

            return error.ToString();
        }
        public static void ConfigureExpectionHandler(this IApplicationBuilder app)
        {
            app.UseExceptionHandler(appError =>
           {
               appError.Run(async e =>
               {
                   e.Response.StatusCode = (int)HttpStatusCode.InternalServerError;
                   e.Response.ContentType = "applications/json";

                   var contex = e.Features.Get<IExceptionHandlerFeature>();
                   if (contex != null)
                   {

                       Logger.Error($"W aplikacji wystąpił błąd: {contex.Error.Message} {getErrorDetail(contex.Error)}");

                       await e.Response.WriteAsync("Wystąpił błąd serwera!");
                   }
               });
           });
        }

    }
}
