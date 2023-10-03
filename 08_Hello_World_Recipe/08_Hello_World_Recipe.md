# Yocto

## 08 Hello World Recipe

### What are you going to learn?

In this video we are going to create a yocto recipe from scratch.




### What Topics we are going to cover?

* A **helloworld.c** program
* **SUMMARY** : A brief description of the Recipe
* **LICENSE** : Which Type of License are we going to use E.g MIT, GPL, BSD etc.
* **LIC_FILES_CHKSUM** : License file location and its **md5** checksum.
* Calculate checksum using **md5sum** utility
* **SRC_URI** : Source Files
* **do_compile**: Here the compilation takes place.
* **do_install** : Here we tells the recipe where to put the binary file in final image.

```bash
touch hello-world_1.0.bb
```
```bash
SUMMARY = "A simple Hello World program"
LICENSE = "MIT"
PR = "r0"

SRC_URI = "file://hello-world.c"

do_compile() {
    ${CC} hello-world.c -o hello-world
}

do_install() {
    install -d ${D}${bindir}
    install -m 0755 hello-world ${D}${bindir}
}

FILES_${PN} += "${bindir}"
```

Create the Source File:

Create a C file named hello-world.c with the following content:

```bash

#include <stdio.h>

int main() {
    printf("Hello, World!\n");
    return 0;
}
```

```bash
source oe-init-build-env build
bitbake core-image-minimal

```

### How to Generate md5 Checksum

```bash
md5sum FILENAME
```

### Reference Link

https://docs.yoctoproject.org/bitbake/2.2/bitbake-user-manual/bitbake-user-manual-metadata.html

### End

In the next video we will learn about bitbake steps.
