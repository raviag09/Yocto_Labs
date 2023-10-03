AUTOMAKE_OPTIONS = foreign

CFLAGS = -Wall -pedantic -O2
include_HEADERS = sharex.h

lib_LTLIBRARIES = libsharex.la
libsharex_la_SOURCES  = sharex.c
libsharex_la_LDFLAGS= -version-info 1:0:0

bin_PROGRAMS = sharelib
sharelib_SOURCES = sharelib.c
sharelib_LDADD = libsharex.la