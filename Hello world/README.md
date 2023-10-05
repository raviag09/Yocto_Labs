# Yocto_Labs
Yocto projects steps to perform the build to customize Embedded distros


###Creating Custom layer
```bash

ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2$ source oe-init-build-env 

```

```bash

ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/build$ bitbake-layers create-layer ../meta-mylayer
NOTE: Starting bitbake server...
Add your new layer with 'bitbake-layers add-layer ../meta-mylayer'
ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/build$ bitbake-layers add-layer ../meta-mylayer
```


```bash

ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/build$ bitbake-layers show-layers
NOTE: Starting bitbake server...
layer                 path                                      priority
==========================================================================
meta                  /home/ubuntu/poky-dunfell-23.0.2/meta     5
meta-poky             /home/ubuntu/poky-dunfell-23.0.2/meta-poky  5
meta-yocto-bsp        /home/ubuntu/poky-dunfell-23.0.2/meta-yocto-bsp  5
meta-mylayer          /home/ubuntu/poky-dunfell-23.0.2/meta-mylayer  6
```




###Creating Hello world source:
Put hello.c and hello_1.0.bb in below given directory structure. ~/poky/meta-mylayer/recipes-hello/hello/files/hello.c

ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/meta-mylayer/recipes-hello/hello$ view ~/poky/meta-mylayer/recipes-hello/hello/files/hello.c

```bash
#include <stdio.h>
int main()
{
printf("Hello hello\n");
printf("Task Done\n");
return 0;
}
```

###Writing Recipe file: (hello_1.0.bb)

ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/meta-mylayer/recipes-hello/hello$ touch hello_1.0.bb

```bash
DESCRIPTION = "Hello-my first recipe"
SECTION = "Mywork"
LICENSE="CLOSED"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/MIT;md5=0835ade698e0bcf8506ecda2f7b4f302"
SRC_URI = "file://hello.c"
S = "${WORKDIR}"
TARGET_CC_ARCH += "${LDFLAGS}"
do_compile() {
         ${CC} hello.c -o hello
}
do_install() {
         install -d ${D}${bindir}
         install -m 0755 hello ${D}${bindir}
}


```

This Recipe file take Source code from SRC_URI variable ,Compile it (do_compile())and install(do_install()) it at “/usr/bin/” location in rootfs.



```bash

ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/meta-mylayer$ tree ./
./
├── COPYING.MIT
├── README
├── conf
│   └── layer.conf
├── hello
├── recipes-example
│   └── example
│       └── example_0.1.bb
└── recipes-hello
    └── hello
        ├── files
        │   └── hello.c
        └── hello_1.0.bb

```

###Selecting Machine and adding package to Rootfs : we set machine variable with our desired machine name and add hello package in Image using IMAGE_INSTALL_append variable.


ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/build/conf$ view local.conf

```bash

MACHINE ??= "qemux86-64"
IMAGE_INSTALL_append = "hello"
```

##Building Image:Build an OS image for the target, which is core-image-minimal in this example.

```bash

ubuntu@ip-172-31-54-223:~/poky-dunfell-23.0.2/build$ bitbake core-image-minimal

```

###Simulate Image Using QEMU

```bash
 $ runqemu qemux86-64
 
 ```
 
 