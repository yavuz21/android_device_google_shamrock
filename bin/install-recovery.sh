#!/system/bin/sh
if ! applypatch -c EMMC:/dev/block/bootdevice/by-name/recovery:32575488:eb6fc1fa68aeb5d1a0398985de414a29d89f2093; then
  applypatch -b /system/etc/recovery-resource.dat EMMC:/dev/block/bootdevice/by-name/boot:31711232:7fa1c31b74e923c607c8450c7c6872cdf549e5f7 EMMC:/dev/block/bootdevice/by-name/recovery eb6fc1fa68aeb5d1a0398985de414a29d89f2093 32575488 7fa1c31b74e923c607c8450c7c6872cdf549e5f7:/system/recovery-from-boot.p && log -t recovery "Installing new recovery image: succeeded" || log -t recovery "Installing new recovery image: failed"
else
  log -t recovery "Recovery image already installed"
fi
