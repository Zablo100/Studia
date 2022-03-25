/* USER CODE BEGIN Header */
/**
  ******************************************************************************
  * @file           : main.c
  * @brief          : Main program body
  ******************************************************************************
  * @attention
  *
  * <h2><center>&copy; Copyright (c) 2021 STMicroelectronics.
  * All rights reserved.</center></h2>
  *
  * This software component is licensed by ST under BSD 3-Clause license,
  * the "License"; You may not use this file except in compliance with the
  * License. You may obtain a copy of the License at:
  *                        opensource.org/licenses/BSD-3-Clause
  *
  ******************************************************************************
  */
/* USER CODE END Header */
/* Includes ------------------------------------------------------------------*/
#include "main.h"
#include "adc.h"
#include "usart.h"
#include "gpio.h"

/* Private includes ----------------------------------------------------------*/
/* USER CODE BEGIN Includes */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdarg.h>
#include <Bufor.h>

/* USER CODE END Includes */

/* Private typedef -----------------------------------------------------------*/
/* USER CODE BEGIN PTD */

/* USER CODE END PTD */

/* Private define ------------------------------------------------------------*/
/* USER CODE BEGIN PD */
/* USER CODE END PD */

/* Private macro -------------------------------------------------------------*/
/* USER CODE BEGIN PM */

/* USER CODE END PM */

/* Private variables ---------------------------------------------------------*/

/* USER CODE BEGIN PV */
buforKolowy Tx, Rx; //Bufory kołowe
char ramkaDane[100]; // Dane ramki z pola dane.
char cmd[3]; // Komenda ramki
char ostatniReset[75]; // Przyczyna ostatniego resetu.
uint8_t ramkaOdbieranie = 0;
uint8_t ostatniResetChceck = 0; // Czy była już sprawdzana przyczyna ostatniego resetu.
uint8_t ramkaZnak; // Pojedynczy znak ramki
uint8_t ramkaDlugosc = 0; // Dlugość ramki
uint8_t ramka[sizeOfBuffor]; // Ramka

/* USER CODE END PV */

/* Private function prototypes -----------------------------------------------*/
void SystemClock_Config(void);
/* USER CODE BEGIN PFP */
void ramkaNadawanie(char dnae[]); // Tworzenie ramki do odesłania
void zlaCmd(); // Błąd informujący o podanie nieznanego polecenia
void ramkaWrrrrCMD(); // Błąd infrmujący o tym że polecenia zawiera inne znaki niż litery
void zbytDlugaRamka();
/* USER CODE END PFP */

/* Private user code ---------------------------------------------------------*/
/* USER CODE BEGIN 0 */
void UART_Begin(){
	Tx.busy = 0;
	Tx.empty = 0;

	Rx.busy = 0;
	Rx.empty = 0;
}
void HAL_UART_RxCpltCallback(UART_HandleTypeDef *huart) {
	if (huart->Instance == USART2) {
		Rx.empty++;
		if (Rx.empty >= sizeOfBuffor) {
			Rx.empty = 0;
		}
		HAL_UART_Receive_IT(&huart2, &Rx.array[Rx.empty], 1);
	}
}

void HAL_UART_TxCpltCallback(UART_HandleTypeDef *huart){
	if(Tx.busy != Tx.empty){
		uint8_t tempChar = Tx.array[Tx.busy];
		Tx.busy++;

		if(Tx.busy >= sizeOfBuffor){
			Tx.busy = 0;
		}
		HAL_UART_Transmit_IT(&huart2, &tempChar, 1);
	}
}

void USART_Send(char* message, ...){
	char tempMsg[105];
	int i;
	volatile int send_idx = Tx.empty;

	va_list arglist;
	va_start(arglist, message);
	vsprintf(tempMsg, message, arglist);
	va_end(arglist);

	for (i = 0; i < strlen(tempMsg); i++) {
		Tx.array[send_idx] = tempMsg[i];
		send_idx++;
		if (send_idx >= sizeOfBuffor) {
			send_idx = 0;
		}
	}

	__disable_irq();
	if ((Tx.empty == Tx.busy) && (__HAL_UART_GET_FLAG(&huart2, UART_FLAG_TXE)==SET)) {
		Tx.empty = send_idx;
		uint8_t tmp = Tx.array[Tx.busy];
		Tx.busy++;
		if (Tx.busy >= sizeOfBuffor)
			Tx.busy = 0;

		HAL_UART_Transmit_IT(&huart2, &tmp, 1);
		}else{
			Tx.empty = send_idx;
		}
	__enable_irq();

}

char* ultoa( unsigned long value, char *string, int radix )
{
  char tmp[33];
  char *tp = tmp;
  long i;
  unsigned long v = value;
  char *sp;

  if ( string == NULL )
  {
    return 0;
  }

  if (radix > 36 || radix <= 1)
  {
    return 0;
  }

  while (v || tp == tmp)
  {
    i = v % radix;
    v = v / radix;
    if (i < 10)
      *tp++ = i+'0';
    else
      *tp++ = i + 'a' - 10;
  }

  sp = string;


  while (tp > tmp)
    *sp++ = *--tp;
  *sp = 0;

  return string;
}

// Unikalne ID urządzenia
void UID(){
	//Zmienne na przechowanie wyników w formie char'a
	char wynik[100] = {0}, wynik1[25], wynik2[25], wynik3[25];

	//Odczytanie numeru seryjnego z adresu, całkowita długość to 96 bit (Strona 839)
	uint32_t uid1 = HAL_GetUIDw0();
	uint32_t uid2 = HAL_GetUIDw1();
	uint32_t uid3 = HAL_GetUIDw2();

	//Konwersja z int'a na char'a

	ultoa(uid1, wynik1, 16);
	ultoa(uid2, wynik2, 16);
	ultoa(uid3, wynik3, 16);


	//Sklejenie wyniku w jednen zmienna
	strcat(wynik, "[Unikalne ID] ");
	strcat(wynik, wynik1);
	strcat(wynik, wynik2);
	strcat(wynik, wynik3);

	//Nadanie odpowiedzi
	ramkaNadawanie(wynik);


}
// Rozmiar pamięci flash urządzenia
void flashSize(){
	// Zmienne na przechowanie wyniku
	uint16_t flash;
	char wynik[100] = {0};
	char wynikFlash[10] = {0};

	//Odczytanie wyniku z adresu. (Strona 840)
	flash = *(uint16_t*) (0x1FFF7A22);

	//Konwersja z uint na char'a
	utoa(flash, wynikFlash, 10);

	strcat(wynik, "[Rozmiar flash] ");
	strcat(wynik, wynikFlash);

	//Nadanie wyniku
    ramkaNadawanie(wynik);
}
// Przyczyna ostatniego resetu
void lastReset(){
	char reset[75]; //Ramka max 100 znaków to tutaj 75 żeby można był jeszcze dodać info "[Ostatni reset]"
	char wynik[100] = {0};
	/**
	  * Strona 740 w HAL
	  * RCC_FLAG_BORRST: POR/PDR or BOR reset.
	  * RCC_FLAG_PINRST: Pin reset.
	  * RCC_FLAG_PORRST: POR/PDR reset.
	  * RCC_FLAG_SFTRST: Software reset.
	  * RCC_FLAG_IWDGRST: Independent Watchdog reset.
	  * RCC_FLAG_WWDGRST: Window Watchdog reset.
	  * RCC_FLAG_LPWRRST: Low Power reset.
	  */

	if(!ostatniResetChceck){ // Sprawdzamy czy czasem nie było już wcześniej sprawdzaniej flagi.
		if (__HAL_RCC_GET_FLAG(RCC_FLAG_LPWRRST)){
	        	strcpy(reset ,"Low power Reset");
	    	}else if (__HAL_RCC_GET_FLAG(RCC_FLAG_IWDGRST)){
	    		strcpy(reset ,"Independent Wathcdog Reset");
	    	}else if (__HAL_RCC_GET_FLAG(RCC_FLAG_SFTRST)){
	    		strcpy(reset ,"Software Reset");
	    	}else if (__HAL_RCC_GET_FLAG(RCC_FLAG_PORRST)){
	    		strcpy(reset ,"Power off Reset");
	    	}else if (__HAL_RCC_GET_FLAG(RCC_FLAG_PINRST)){
	    		strcpy(reset ,"Pin reset");
	    	}else if (__HAL_RCC_GET_FLAG(RCC_FLAG_WWDGRST)){
	    		strcpy(reset ,"Window Watchdog reset");
	    	}else if (__HAL_RCC_GET_FLAG(RCC_FLAG_BORRST)){
	    		strcpy(reset ,"POR/PDR or BOR reset");
	    	}else if (__HAL_RCC_GET_FLAG(RCC_FLAG_PORRST)){
	    		strcpy(reset ,"POR/PDR reset");
	    	}else{
	    		strcpy(reset ,"Nieznany");
	    	}
		ostatniResetChceck = 1;       //Zmieniam stan sprawdzone
		strcpy(ostatniReset, reset); // Kopiujemy przyczyne resetu do zmiennej globalnej
	}else{                           //Jeżeli była już sprawdzana przyczyna ostatniego resetu to
		strcpy(reset, ostatniReset); // Kopiujemy przyczyne ostatniego resetu ze zmiennej globalnej, aby ją wyświetlić
	}

	//Czyszczenie flagi
	__HAL_RCC_CLEAR_RESET_FLAGS();


	//Sklejenie odpowiedzi
	strcat(wynik, "[Ostatni reset] ");
	strcat(wynik, reset);

	//Nadanie wyniku
	ramkaNadawanie(wynik);

}
// Sprawdzenie wersji biblioteki HAL
void checkHAL(){
	char wynik[100] = {0}, wynik2[75];


	uint32_t wersja = HAL_GetHalVersion();

	ultoa(wersja, wynik2, 10);

	strcat(wynik, "[Wersja HAL] ");
	strcat(wynik, wynik2);

	ramkaNadawanie(wynik);

}

void checkTemp(){
	//Storna 112 do procesora/ 226 płytka
	const float V25 = 0.76; // Wartość temperatur dla 25 stopni C
	const float Avg_slope = 0.0025; //
	const float SupplyVoltage = 3.3; // Napięcie
	const float ADCResolution = 4095.0;

/*
uint16_t T30 = TEMPSENSOR_CAL1_TEMP;
uint16_t T110 = TEMPSENSOR_CAL2_TEMP;

(251) ???
https://www.st.com/resource/en/reference_manual/dm00031936-stm32f0x1-stm32f0x2-stm32f0x8-advanced-arm-based-32-bit-mcus-stmicroelectronics.pdf
Temperature = (110-30)/(T110-T30)*(PomiarADC - T30) + 30;
*/
	uint16_t PomiarADC;
	double Temperature;
	float Vsens;

	char wynikTemp[25];
	char wynik[100] = {0};


	HAL_ADC_Start(&hadc1);

	if (HAL_ADC_PollForConversion(&hadc1, 100) == HAL_OK){

		PomiarADC = HAL_ADC_GetValue(&hadc1); // Pobiera zmierzone wartości.

		Vsens = (SupplyVoltage*PomiarADC)/ADCResolution; // Przelicza zmierzone wartości na napięcie
		Temperature = ((Vsens-V25)/Avg_slope)+25;

	}

	itoa(Temperature, wynikTemp, 10);


	strcat(wynik, "[Temperatura CPU] ");
	strcat(wynik, wynikTemp);
	strcat(wynik, " C");

	ramkaNadawanie(wynik);
}


// Informacji zwrotna o podanej komendzie
void cmdInfo(){
	USART_Send("[Komenda] ");
	USART_Send(cmd);
	USART_Send("\r\n");
}

// Sprawdzenie czy w polu komendy nie ma cyfr.
uint8_t chceckCmd(){

	//Sprawdzanie czy wszystkie 3 znaki koemendy to literki
	for (int i=0;i<3;i++){
		if(!isalpha((unsigned char)cmd[i])){
			ramkaWrrrrCMD();
			return 0;
		}
	}
	return 1;
}
// Analiza i wykonanie polecenia
void command(){

	if(chceckCmd()){
		if (cmd[0] == 'R'  && cmd[1] == 'T' && cmd[2] == 'M'){
			cmdInfo();
			checkTemp();
		}else if (cmd[0] == 'R' && cmd[1] == 'F' && cmd[2] == 'L'){
			cmdInfo();
			flashSize();
		}else if (cmd[0] == 'R' && cmd[1] == 'I' && cmd[2] == 'D'){
			cmdInfo();
			UID();
		}else if (cmd[0] == 'R' && cmd[1] == 'H' && cmd[2] == 'L'){
			cmdInfo();
			checkHAL();
		}else if (cmd[0] == 'R' && cmd[1] == 'P' && cmd[2] == 'R'){
			cmdInfo();
			lastReset();
		}else{
			zlaCmd();
			USART_Send("\r\n");
		}
	}
}


void ramkaNadawanie(char dane[]){
	char msg[105] = ":OUT"; // 105 bo taka jest max długość ramki
	strcat(msg,dane);
	strcat(msg,";");
	USART_Send(msg);

}

void zlaCmd(){
	char msg[105] = ":OUT[Error] Podana komenda jest nieprawidlowa;";
	USART_Send(msg);
	USART_Send("\r\n");
}
void zbytDlugaRamka(){
	char msg[105] = ":OUT[Error] ramka jest zbyt dluga;";
	USART_Send(msg);
	USART_Send("\r\n");
}
void ramkaWrrrrCMD(){
	char msg[105] = ":OUT[Error] W polu komendy sa inne znaki niz dozwolone;";
	USART_Send(msg);
	USART_Send("\r\n");
}

/* USER CODE END 0 */

/**
  * @brief  The application entry point.
  * @retval int
  */
int main(void)
{
  /* USER CODE BEGIN 1 */
	UART_Begin(); // Ustawiamy wszystkie zmienne związane z buforem kołowym.
  /* USER CODE END 1 */

  /* MCU Configuration--------------------------------------------------------*/

  /* Reset of all peripherals, Initializes the Flash interface and the Systick. */
  HAL_Init();

  /* USER CODE BEGIN Init */

  /* USER CODE END Init */

  /* Configure the system clock */
  SystemClock_Config();

  /* USER CODE BEGIN SysInit */

  /* USER CODE END SysInit */

  /* Initialize all configured peripherals */
  MX_GPIO_Init();
  MX_USART2_UART_Init();
  MX_ADC1_Init();
  /* USER CODE BEGIN 2 */
  HAL_UART_Receive_IT(&huart2, &Rx.array[Rx.empty], 1); // USART przerwania
  /* USER CODE END 2 */

  /* Infinite loop */
  /* USER CODE BEGIN WHILE */

  while (1)
  {
	  if (Rx.busy != Rx.empty){ 		// Jak jest jakiś znak do odebrania
		  ramkaZnak = Rx.array[Rx.busy]; // Pobieramy znak z bufora

		  Rx.busy++;					 // Zwiększamy wskaźnik o 1
		  if (Rx.busy >= sizeOfBuffor){ // Sprawdzamy czy wskaźnik nie wykracza poza bufora
			  Rx.busy = 0; 				// Jak jest to ustawiamy go na początek bufora
		  }
		  if (ramkaZnak == 0x3A){ // Jeżeli znak początka ramki
			  ramkaOdbieranie = 1;
			  ramkaDlugosc = 0;
		  }

		  if (ramkaOdbieranie == 1){
			  ramka[ramkaDlugosc] = ramkaZnak; // Wkładamy pobrany znak z bufora do ramki
			  ramkaDlugosc++; 					// Zwiększamy długość ramki bo pobraliśmy znak

			  if (ramkaDlugosc > 104){ // Liczymy znak początku i końca. (104 bo od zera jak w tablicy)
				  zbytDlugaRamka();
				  ramkaOdbieranie = 0;
				  ramkaDlugosc = 0;
			  }

				if (ramkaZnak == 0x3B && ramkaDlugosc > 0){ // Jeżeli znak końca ramki to
					memcpy(cmd, &ramka[1], 3); 			//Kopiujemy pierwsze 3 znak ramki do zmiennej cmd (Pierwsze 3 znaki w moim przypadku to komenda)
					memcpy(ramkaDane, &ramka[4], 100);  // Pobieramy znaki z ramki do zmiennej dane (u mnie 100 znaków)
					command(); 							// Wywołujemy funkcje odpowiedzialną za wykonanie komendy
					ramkaDlugosc = 0; 					//Po wykonaniu komendy resetujemy długość ramki
					ramkaOdbieranie = 0;			   // Odebraliśmy ramkę, więc stan odbierania na 0
					memset(ramkaDane, 0, 100); 			// Czyścimy dane ramki żeby nic tam nie zostało
					USART_Send("\r\n"); 				// Wysyłamy nową linie dla bardziej przejrzystego wyglądu w konsoli
				}
		  }
	  }
  }
    /* USER CODE END WHILE */

    /* USER CODE BEGIN 3 */

  /* USER CODE END 3 */
}

/**
  * @brief System Clock Configuration
  * @retval None
  */
void SystemClock_Config(void)
{
  RCC_OscInitTypeDef RCC_OscInitStruct = {0};
  RCC_ClkInitTypeDef RCC_ClkInitStruct = {0};

  /** Configure the main internal regulator output voltage
  */
  __HAL_RCC_PWR_CLK_ENABLE();
  __HAL_PWR_VOLTAGESCALING_CONFIG(PWR_REGULATOR_VOLTAGE_SCALE2);
  /** Initializes the RCC Oscillators according to the specified parameters
  * in the RCC_OscInitTypeDef structure.
  */
  RCC_OscInitStruct.OscillatorType = RCC_OSCILLATORTYPE_HSI;
  RCC_OscInitStruct.HSIState = RCC_HSI_ON;
  RCC_OscInitStruct.HSICalibrationValue = RCC_HSICALIBRATION_DEFAULT;
  RCC_OscInitStruct.PLL.PLLState = RCC_PLL_NONE;
  if (HAL_RCC_OscConfig(&RCC_OscInitStruct) != HAL_OK)
  {
    Error_Handler();
  }
  /** Initializes the CPU, AHB and APB buses clocks
  */
  RCC_ClkInitStruct.ClockType = RCC_CLOCKTYPE_HCLK|RCC_CLOCKTYPE_SYSCLK
                              |RCC_CLOCKTYPE_PCLK1|RCC_CLOCKTYPE_PCLK2;
  RCC_ClkInitStruct.SYSCLKSource = RCC_SYSCLKSOURCE_HSI;
  RCC_ClkInitStruct.AHBCLKDivider = RCC_SYSCLK_DIV1;
  RCC_ClkInitStruct.APB1CLKDivider = RCC_HCLK_DIV1;
  RCC_ClkInitStruct.APB2CLKDivider = RCC_HCLK_DIV1;

  if (HAL_RCC_ClockConfig(&RCC_ClkInitStruct, FLASH_LATENCY_0) != HAL_OK)
  {
    Error_Handler();
  }
}

/* USER CODE BEGIN 4 */

/* USER CODE END 4 */

/**
  * @brief  This function is executed in case of error occurrence.
  * @retval None
  */
void Error_Handler(void)
{
  /* USER CODE BEGIN Error_Handler_Debug */
  /* User can add his own implementation to report the HAL error return state */
  __disable_irq();
  while (1)
  {
  }
  /* USER CODE END Error_Handler_Debug */
}

#ifdef  USE_FULL_ASSERT
/**
  * @brief  Reports the name of the source file and the source line number
  *         where the assert_param error has occurred.
  * @param  file: pointer to the source file name
  * @param  line: assert_param error line source number
  * @retval None
  */
void assert_failed(uint8_t *file, uint32_t line)
{
  /* USER CODE BEGIN 6 */
  /* User can add his own implementation to report the file name and line number,
     ex: printf("Wrong parameters value: file %s on line %d\r\n", file, line) */
  /* USER CODE END 6 */
}
#endif /* USE_FULL_ASSERT */

/************************ (C) COPYRIGHT STMicroelectronics *****END OF FILE****/
