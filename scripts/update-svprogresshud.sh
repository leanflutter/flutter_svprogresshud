#!/usr/bin/env bash

git pull --recurse-submodules

rm -rf ios/SVProgressHUD
cp -R SVProgressHUD/SVProgressHUD ios/SVProgressHUD