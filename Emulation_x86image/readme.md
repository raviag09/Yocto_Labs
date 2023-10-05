Building an x86 Emulation Image with python package  full example illustration on yocto linux

Building an x86 emulation image with specific Python packages using the Yocto Project involves creating a custom image recipe that includes the desired Python packages. Here's a step-by-step example illustration:

Note: This example assumes you have set up the Yocto Project build environment as described in previous responses.

Clone the Yocto Project Repository:

If you haven't already, clone the Yocto Project repository to your workspace:

```bash

git clone git://git.yoctoproject.org/poky
```

run this command to initiate the build
```bash
source oe-init-build-env
```

create a custom meta layer:

```bash
bitbake-layers create-layer /home/ubuntu/poky-dunfell-23.0.2/meta-custom
```
source oe-init-build-env meta-custom
ubuntu@ip-172-31-48-255:~/poky-dunfell-23.0.2$ bitbake-layers add-layer /home/ubuntu/poky-dunfell-23.0.2/meta-custom
```

Initialize the Build Environment:

Change to the poky directory and initialize the build environment:

```bash

cd ~/poky-dunfell-23.0.2/meta-custom
source oe-init-build-env meta-custom
```


Customize Configuration:

Edit the conf/local.conf file (~/poky-dunfell-23.0.2/meta-custom/conf$ view local.conf) to customize your build. You can specify the target machine and other build settings. For example:

```bash

MACHINE ??= "qemux86-64"
IMAGE_FEATURES += "ssh-server-openssh"
```

The qemu-x86-64 machine represents a 64-bit x86 QEMU emulator.

Create a Custom Image Recipe:

Create a custom image recipe to define the contents and configuration of your x86 emulation image. Create a file named my-x86-image.bb (or any name you prefer) in the directory:
ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/meta-custom/recipes-example$ mkdir images
ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/meta-custom/recipes-example$ touch my-x86-image.bb
ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/meta-custom/recipes-example$ view my-x86-image.bb


```bash

touch /home/ubuntu/poky-dunfell-23.0.2/meta-custom/recipes-example/images/my-x86-image.bb
```

Edit the my-x86-image.bb file and define your custom image recipe. In this example, we include specific Python packages (e.g., python3-package1 and python3-package2) in the image:

```bash

DESCRIPTION = "Custom x86 Emulation Image"
LICENSE = "MIT"

# Specify the packages to include in the image
IMAGE_INSTALL += "qemu qemu-native"

```

Replace python3-package1 and python3-package2 with the names of the Python packages you want to include in your image.

Add the Custom Image to the Image List:

Edit the /conf/local.conf file again to add your custom image to the list of images to be built:

```bash
IMAGE_FSTYPES_append = " wic.gz"
IMAGE_CLASSES += "image_types"
IMAGE_TYPES = "wic wic.gz"
IMAGE_NAME_wic = "my-x86-image"
```
edit the bb layers by adding the custom layer
```bash
BBLAYERS ?= " \
  /home/ubuntu/poky-dunfell-23.0.2/meta \
  /home/ubuntu/poky-dunfell-23.0.2/meta-poky \
  /home/ubuntu/poky-dunfell-23.0.2/meta-yocto-bsp \
  /home/ubuntu/poky-dunfell-23.0.2/meta-custom \
   "
   ```
Build Your Custom x86 Emulation Image with Python Packages:

Build your custom x86 emulation image with the specified Python packages using the bitbake command:

```bash
bitbake core-image-minimal
```

Run QEMU with Your Custom Image:

After the build is complete, you can run the QEMU emulator with your custom x86 image. For example, to run the 64-bit x86 QEMU emulator:

```
runqemu qemu-x86-64 nographic slirp
```

This command launches QEMU with the specified options. The nographic option runs QEMU without a graphical interface, and slirp provides basic networking.

Access the Emulated System:

You can access the emulated x86 system via SSH (if you included the ssh-server-openssh feature) or by using the QEMU monitor console.

This example demonstrates how to set up and build a custom x86 emulation image with specific Python packages using Yocto Project. You can tailor your image by adding or removing Python packages according to your project's requirements.


