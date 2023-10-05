###Required Packages for the Build Host

```bash
sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
     build-essential chrpath socat cpio python3 python3-pip python3-pexpect \
     xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev \
     pylint3 xterm
```     

###Cloning Poky Repository
 
```bash     
     git clone git://git.yoctoproject.org/poky -b dunfell
     
     
     git clone https://git.openembedded.org/meta-openembedded -b dunfell
 
 ```
     
###Initialize the Build Environment     
 
```bash
     $ cd ~/poky
$ source oe-init-build-env
```

###Start the Build - Build an OS image for the target

```bash
$ bitbake core-image-minimal
```

###Simulate Image Using QEMU

```bash

$ runqemu qemux86
```

###Find Kernel Source: Go to Location “poky/build/tmp/work-shared/qemux86–64/kernel-source”.

```bash
$cd poky/build/tmp/work-shared/qemux86–64/kernel-source
$make kernelversion
```

### Modifying USB Driver:Go to usb.c or usb-skeleton.c location and add some printk statements in __init function


```bash
$cd poky/build/tmp/work-shared/qemux86-64/kernel-source/drivers/usb
$vi usb.c
Go to usb_init function and add below lines:
printk(KERN_INFO"hello :USB Driver Initialized"); printk(KERN_INFO"hello :Patch applied successfully");
printk(KERN_INFO"hello: Done");
```



### Commit Modify Changes: Commit these changes and Come back to kernel source location.

run from location poky/build/tmp/work-shared/qemux86-64/kernel-source/

```bash
$git add .
$git commit -m "printstatementsadded"
$cd poky/build/tmp/work-shared/qemux86–64/kernel-source
```

run from location poky/build/tmp/work-shared/qemux86-64/kernel-source/

### Making Git patch :make patch of last commit.

```bash
$git format-patch HEAD~
```

### Adding patch to kernel Recipe

Adding patch to kernel Recipe: find your kernel recipe(in my case it is linux-yocto_5.2.bb) and make folder(linux-yocto) with your recipe name paste your patch there.

```bash
cd poky/meta/recipes-kernel/linux
$mkdir linux-yocto
```

### Add this statement to linux-yocto_5.2.bb file.

```bash

SRC_URI += "file://0001-printstatementsadded.patch"

```

### Building Image: come back to poky home location ,build it and run on quick emulator.

```bash

$source oe-init-build-env
$bitbake core-image-minimal
$runqemu qemux86

```

### Checking Boot Logs: After login with root check dmesg of machine and find our print statements in it .

```bash

$dmesg > log.txt
$vi log.txt
find your message in it.

```

