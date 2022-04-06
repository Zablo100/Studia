using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PSW3
{
    public class RGB
    {
        public int rValue { get; set; }
        public int gValue { get; set; }
        public int bValue { get; set; }

        public RGB(int rValue, int gValue, int bValue)
        {
            this.rValue = rValue;
            this.gValue = gValue;
            this.bValue = bValue;
        }

    }
}
