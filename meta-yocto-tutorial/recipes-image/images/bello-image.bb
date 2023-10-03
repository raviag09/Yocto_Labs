require recipes-core/images/core-image-minimal.bb

DESCRIPTION = "A small image just capable of allowing a device to boot and \
is suitable for development work."

IMAGE_FEATURES += "dev-pkgs"

IMAGE_INSTALL += " bello"
IMAGE_INSTALL += " sharelib"
IMAGE_INSTALL += " bbexample"
IMAGE_INSTALL += " this-is"
IMAGE_INSTALL += " paho-mqtt-c"
