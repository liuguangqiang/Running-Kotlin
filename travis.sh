#!/bin/bash

if [[ $TRAVIS_PULL_REQUEST != false ]]
then

	fastlane test

elif [[ $TRAVIS_BRANCH == dev ]]
then

	fastlane dev

elif [[ $TRAVIS_BRANCH == staging ]]
then

	fastlane dev

elif [[ $TRAVIS_BRANCH == master ]]
then

	fastlane dev

elif [[ $TRAVIS_BRANCH == production ]]
then

	fastlane deploy

fi