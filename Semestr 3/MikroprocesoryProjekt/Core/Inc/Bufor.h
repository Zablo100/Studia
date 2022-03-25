#include "main.h"

#define sizeOfBuffor 1024

typedef struct buforKolowy_{
	volatile uint16_t busy;
	volatile uint16_t empty;
	uint8_t array[sizeOfBuffor];

}buforKolowy;
