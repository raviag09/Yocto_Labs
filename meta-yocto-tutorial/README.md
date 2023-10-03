
# yocto

1. Download the source code file:
- 💻 *$* `mkdir yocto`
  - 📌 Create a folder to contain source code
- 💻 *$* `cd yocto`
- 💻 *yocto$* `git clone https://github.com/thanhduongvs/meta-yocto-tutorial`
  - 📌 meta-yocto-tutorial is a sample layer I created myself
- 💻 *yocto$* `git clone https://git.yoctoproject.org/git/poky`
  - 📌 This is the source code of Yocto Project
- 💻 *$* `poky cd`
- 💻 *yocto/poky$* `git checkout yocto-2.6.4`
  - 📌 In this example, I use branch yocto-2.6.4
  - ⚠️ The command will be different if you use yocto project smaller than 2.4

2. Build yocto:
- 💻 *yocto/poky$* `source oe-init-build-env`
  - 📌 set environment variables
- 📝 Open the file **poky/build/conf/bblayers.conf** and add the path *meta-yocto-tutorial* to yocto,
  - 📌 For example: /home/vanson/yocto/meta-yocto-tutorial

    ```
    # POKY_BBLAYERS_CONF_VERSION is increased each time build/conf/bblayers.conf
    # changes incompatibly
    POKY_BBLAYERS_CONF_VERSION = "2"

    BBPATH = "${TOPDIR}"
    BBFILES ?= ""

    BBLAYERS ?= " \
    /home/vanson/yocto/poky/meta \
    /home/vanson/yocto/poky/meta-poky \
    /home/vanson/yocto/poky/meta-yocto-bsp \
    /home/vanson/yocto/meta-yocto-tutorial \
    "
    ```
- 💻 *yocto/poky/build$* `bitbake bello-image --runall=fetch`
  - 📌 In this step, yocto will download packages for building
  - ℹ️ For yocto smaller than 2.4 the above command is replaced with `bitbake -c fetchall bello-image`
- 💻 *yocto/poky/build$* `bitbake -k bello-image`
  - 📌 In this step yocto will build the image

3. Run virtual machine:
- 💻 *yocto/poky/build$* `runqemu qemux86`
  - ⚠️ Then enter your computer's password
  - ⚠️ To log into the virtual machine type `root`

- 💻 *yocto/poky/build$* `cd ../../`
- 💻 *$* `bitbake-layers create-layer meta-live`
- 💻 *$* `bitbake-layers show-layers`
- 💻 *$* `bitbake-layers show-layers`
- 💻 */poky/build$* `bitbake -f example