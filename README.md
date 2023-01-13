# dogfooding Android Library

## Project status

<p align="center">
<a href='https://android-arsenal.com/api?level=19'><img alt='MIN API' src='https://img.shields.io/badge/min%20api-19-blue?style=for-the-badge'/></a>
<a href='https://developer.android.com/studio/releases#3-4-2'><img alt='ANDROID STUDIO PLUGIN VERSION' src='https://img.shields.io/badge/android%20studio-3.4.2-blue?style=for-the-badge'/></a>
<a href='https://docs.gradle.org/5.6.2/release-notes.html'><img alt='GRADLE VERSION' src='https://img.shields.io/badge/gradle-5.6.2-blue?style=for-the-badge'/></a>
</p>

## What is this?

TODO: complete with project description

## Contributing

This is not written in rock like you must be guessing so write us and let's make everyone's life a little easier.

Remember to always follow these **[guidelines](https://sites.google.com/mercadolibre.com/mobile/nuevos-proyectos/requerimientos-para-prod)**.

For more information about in house distribution please check [the wiki](https://sites.google.com/mercadolibre.com/mobile/arquitectura/in-house-distribution-mds)

## Repo usage

Create branch or fork from *develop*, then push or create pull requests (if you don't have access) to that branch.

**dogfooding** uses this branching model:

https://furydocs.io/mobile-apps/guide/#/lang-en/development/02_development-flow?id=mobile-libraries-development-flow

## Developing and contribuing

### Running checks

We run [SCA](https://github.com/Monits/static-code-analysis-plugin) checks for static code analysis and some built in Android lints among all tests. To run everything:

``` bash
./gradlew check
```

and if you want to check an specific module you can:

``` bash
./gradlew module:check
```

### Getting coverage report

If you want to get a full Jacoco coverage report, you can run the following task:

``` bash
./gradlew jacocoFullReport
```

### Compiling locally

#### Testapp

For testing your module locally you can run the integrated **testapp**. If you want to build it:

``` bash
./gradlew testapp:assembleDebug
```

or if you have already a device connected to **adb**:

``` bash
./gradlew testapp:installDebug
```

#### Pre-release versions

For testing your module in other application (such as the main application or other testapp) you can publish your module locally:

```bash
./gradlew app:publishLocal
```

or if you want to be published for everyone:

```bash
./gradlew app:publishExperimental
```

### Deploying

For deploying you **must** create a branch named _release/X.Y_ where X.Y is the major and minor number to be published. Once you have created it, push your changes and run the command `fury create-version`.

## Testing UI
For UI Tests we are using Appium and Nativefat Library. To download Nativefat you have to clone the repository:
https://github.com/mercadolibre/fury_nativefat-cli

Once cloned, check necessary dependencies in Readme file and execute the script in the root folder of Nativefat.

```ruby
bash install.sh
```

To create the test scaffolding, you need to execute the script below in the root folder of the mobile library:

nativefat init -e appium
You can add the plataform with the parameters: -p ios / -p android / -p mobile (For both).

For more infos about how to install Nativefat, check the Readme file below:
https://github.com/mercadolibre/fury_nativefat-cli/blob/master/README.md

## FAQ

### Having issues accessing to files though URI?

If you are having a `NullPointerException`, this is because you are missing the provider in your manifest to provide your testapp the permission to access to files. Add this snippet to your `AndroidManifest.xml` from your testapp file:

``` xml

<!-- Needed for secure management of files. It is not longer permitted to access a file with file:///-->
<!-- https://developer.android.com/reference/android/support/v4/content/FileProvider.html -->
<provider
    android:name="android.support.v4.content.FileProvider"
    android:authorities="${applicationId}.provider"
    android:exported="false"
    android:grantUriPermissions="true">
    <meta-data
        android:name="android.support.FILE_PROVIDER_PATHS"
        android:resource="@xml/resource_file_provider_path" />
</provider>
```

And create `resource_file_provider_path.xml` under `res/xml`, which content should be:

``` xml

<?xml version="1.0" encoding="utf-8"?>
<paths>
    <external-path name="external_files" path="."/>
</paths>

```
