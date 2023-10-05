One of the favorite and easy-to-use tools to create stand-alone toolchains on the embedded Linux domain is Crosstool-NG [2].
 It’s an open-source utility that supports different architectures including ARM, x86, PowerPC, and MPIC, and has a menuconfig-style interface similar to the one of Linux kernel.



To use it, first install the dependencies:

```bash
~$ sudo apt-get update
~$ sudo apt-get install automake bison chrpath flex g++ git gperf gawk help2man libexpat1-dev libncurses5-dev libsdl1.2-dev libtool libtool-bin libtool-doc python2.7-dev texinfo
```

 Get the source code:

```bash
~$ git clone https://github.com/crosstool-ng/crosstool-ng.git
~$ cd crosstool-ng/
~/crosstool-ng$ git checkout crosstool-ng-1.24.0
```

 Build and install:
 
```bash
~/crosstool-ng$ ./bootstrap
~/crosstool-ng$ ./configure --enable-local
~/crosstool-ng$ make
~/crosstool-ng$ sudo make install
```

Creating the toolchain for RPi 4
After building Crosstool-NG, the next step is to create the toolchain for RPi 4.

Selecting a base-line configuration
By default, Crosstool-NG comes with a few ready-to-use configurations. You can see the full list by typing:

```bash
~/crosstool-ng$ ./ct-ng list-samples
```



The one from the list that is close to our target is aarch64-rpi3-linux-gnu. To get more details about it, run:

```bash
~/crosstool-ng$ ./ct-ng show-aarch64-rpi3-linux-gnu
```



You will see something like:

```bash
[L...]   aarch64-rpi3-linux-gnu
    Languages       : C,C++
    OS              : linux-4.20.8
    Binutils        : binutils-2.32
    Compiler        : gcc-8.3.0
    C library       : glibc-2.29
    Debug tools     : gdb-8.2.1
    Companion libs  : expat-2.2.6 gettext-0.19.8.1 gmp-6.1.2 isl-0.20 libiconv-1.15 mpc-1.1.0 mpfr-4.0.2 ncurses-6.1 zlib-1.2.11
    Companion tools :
	
	Select aarch64-rpi3-linux-gnu as a base-line configuration by typing:
```


```bash
~/w/crosstool-ng$ ./ct-ng aarch64-rpi3-linux-gnu

```


To start the customization, open menuconfig:

```bash

~/w/crosstool-ng$ ./ct-ng menuconfig
```


Make 3 changes:

1. Allow extending the toolchain after it is created (by default, it is created as read-only):

Paths and misc options -> Render the toolchain read-only ->false

2. Change the ARM Cortex core:

Target options -> Emit assembly for CPU: Change cortex-a53to cortex-a72

3. Chane the tuple’s vendor string:

Toolchain options -> Tuple’s vendor string: Change rpi3 torpi4


Build
To create the toolchain, type:

```bash
~/crosstool-ng$ ./ct-ng build
The toolchain will be named aarch64-rpi4-linux-gnu and created in ${HOME}/x-tools/aarch64-rpi4-linux-gnu.

```


Using the toolchain
Hello world!
To test the toolchain, I created a simple hello world program. If we can compile and run it on target, we can say that the toolchain works.

The code is as simple as:

```bash
#include<iostream>

int main(){
  std::cout << "Hello world! \n";
  return 0;
}

```



In order to compile it using our toolchain, first, we need to add the bin dir to PATH so that we can use the tools:

```bash
$ PATH=$PATH:~/x-tools/aarch64-rpi4-linux-gnu/bin

Compile using the following command:

```bash

~/$ aarch64-rpi4-linux-gnu-g++ hello_world.cpp -o hello_world

