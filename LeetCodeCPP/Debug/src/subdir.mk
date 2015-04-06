################################################################################
# Automatically-generated file. Do not edit!
################################################################################

# Add inputs and outputs from these tool invocations to the build variables 
CPP_SRCS += \
../src/LeetCodeCPP.cpp 

OBJS += \
./src/LeetCodeCPP.o 

CPP_DEPS += \
./src/LeetCodeCPP.d 


# Each subdirectory must supply rules for building sources it contributes
src/LeetCodeCPP.o: ../src/LeetCodeCPP.cpp
	@echo 'Building file: $<'
	@echo 'Invoking: GCC C++ Compiler'
	g++ -I/usr/include/c++ -O0 -g3 -Wall -c -fmessage-length=0 -MMD -MP -MF"$(@:%.o=%.d)" -MT"src/LeetCodeCPP.d" -o "$@" "$<"
	@echo 'Finished building: $<'
	@echo ' '


