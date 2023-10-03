Setting up the Build system

The Raspberry Pi and similar single board computers are very popular nowadays. The possibilities are almost endless. From a Home Server to a Media Station to IoT Projects, there’s everything. The two things that make it so easy to use are probably the big community and the ready to start SD-Card Images like Raspian. 
 
The steps for setting up the build environment are fairly simple, but you’ll need some time for the build to complete. That’s the downside of building a Linux Image from scratch. Everything has to be build and this is CPU, RAM, and HDD intensive. On ordinary computers, it can take up to 8 hours. But don’t worry, after the first build is finished the good caching algorithms of Yocto kicks in, and only new and changed stuff is built. The minimal free disk space on your computer has to be at least 50 GB. (For reference: I’ve built this image on a 16-Core AWS Cloud Instance in around 1 hour. The built time is heavily dependent on CPU power and download speed. 

```bash
sudo apt-get install gawk wget git-core diffstat unzip texinfo gcc-multilib \
     build-essential chrpath socat cpio python3 python3-pip python3-pexpect \
     xz-utils debianutils iputils-ping python3-git python3-jinja2 libegl1-mesa libsdl1.2-dev \
     pylint3 xterm
```


Getting the meta layers
All meta layers are usually available via git. Don’t worry for now, if you have no clue what git means. We’re just using one command for now. The git clone command gets the repository from the internet. The -b dunfell switch we're using specifies which version to get. At the time of writing the dunfell version is the most recent version with long-term support

```bash
mkdir yocto
cd yocto
mkdir sources
```

The meta layer we’re always needing is poky. 

```bash
git clone git://git.yoctoproject.org/poky -b dunfell
```

For the Raspberry Pi, there is a nicely made meta layer with all the definitions needed to run the Raspberry Pi.

```bash
git clone git://git.yoctoproject.org/meta-raspberrypi -b dunfell
```


Meta layers always state on which meta layers they depend. The readme of meta-raspberrypi states that we need poky, which we already have, and meta-openembedded. This meta-layer itself is divided into multiple layers. We get them all by this command.

```bash
git clone https://git.openembedded.org/meta-openembedded -b dunfell
```

Let's run it from build folder

```bash
/yocto$ source poky/oe-init-build-env
```


The bblayers.conf file in the conf folder contains all the path to the meta layers we're gonna use.

```bash
nano conf/bblayers.conf
```


POKY_BBLAYERS_CONF_VERSION = "2"

BBPATH = "${TOPDIR}"
BBFILES ?= ""

BBLAYERS ?= " \
  /home/ubuntu/yocto/poky/meta \
  /home/ubuntu/yocto/poky/meta-poky \
  /home/ubuntu/yocto/poky/meta-yocto-bsp \
  /home/ubuntu/yocto/poky/meta-raspberrypi \
"

Last but not least, the local.conf file in the conf folder does contain some basic configurations and, most important for us, the name of the machine to build for. If we want to build for the Raspberry Pi 4, we're using raspberrypi4, for the Raspberry Pi 3, raspberrypi3. This is one of the big benefits of using Yocto. It's just a matter of changing one configuration line to build the same Image for another system.

```bash
nano conf/local.conf

MACHINE ?= "raspberrypi4"
```

With all these steps done, it’s time to start the actual build and get some

```bash
bitbake core-image-base
```


This file is compressed. To decompress it, use the bzip2 command or a tool like 7zip.

bzip2 -d -f tmp/deploy/images/raspberrypi4/core-image-base-raspberrypi4.wic.bz2


flash it to an SD-Card like any other Raspian image, and you’re ready to boot. https://www.raspberrypi.org/documentation/installation/installing-images/


