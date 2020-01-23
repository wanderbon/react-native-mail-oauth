# react-native-mail-oauth

## Getting started

`$ npm install react-native-mail-oauth --save`

### Mostly automatic installation

`$ react-native link react-native-mail-oauth`

### Manual installation


#### iOS

1. In XCode, in the project navigator, right click `Libraries` ➜ `Add Files to [your project's name]`
2. Go to `node_modules` ➜ `react-native-mail-oauth` and add `MailOauth.xcodeproj`
3. In XCode, in the project navigator, select your project. Add `libMailOauth.a` to your project's `Build Phases` ➜ `Link Binary With Libraries`
4. Run your project (`Cmd+R`)<

#### Android

1. Open up `android/app/src/main/java/[...]/MainApplication.java`
  - Add `import com.reactlibrary.MailOauthPackage;` to the imports at the top of the file
  - Add `new MailOauthPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-mail-oauth'
  	project(':react-native-mail-oauth').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-mail-oauth/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-mail-oauth')
  	```


## Usage
```javascript
import MailOauth from 'react-native-mail-oauth';

// TODO: What to do with the module?
MailOauth;
```
