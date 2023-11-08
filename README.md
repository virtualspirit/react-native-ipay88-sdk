# React Native ipay88-sdk

Bridge component for iPay88 SDK (IOS & Android).

`Android` IPay SDK Version: `v.1.6.8` update 31052023 

`IOS` IPay SDK Version: `v.1.0.6.7` udpate 30062026

## Getting started

`$ yarn add https://github.com/virtualspirit/react-native-ipay88-sdk.git`

## Usage

```javascript
import React, { Component } from "react";
import {
  Platform,
  StyleSheet,
  Button,
  Text,
  View,
  Alert,
  ToastAndroid
} from "react-native";
import IPay88, { Pay } from "ipay88-sdk";

export default class App extends Component {
  successNotify = data => {
    if (Platform.OS === "ios") {
      const {
        transactionId,
        referenceNo,
        amount,
        remark,
        authorizationCode
      } = data;

      Alert.alert("Message", `Payment authcode is ${authorizationCode}`, {
        cancelable: true
      });
    } else {
      ToastAndroid.show(
        `Message: Payment authcode is ${authorizationCode}`,
        ToastAndroid.LONG
      );
    }
  };

  cancelNotify = data => {
    const { transactionId, referenceNo, amount, remark, error } = data;

    if (Platform.OS === "ios") {
      Alert.alert("Message", `${error}`, { cancelable: true });
    } else {
      ToastAndroid.show(`Message: ${error}`, ToastAndroid.LONG);
    }
  };

  failedNotify = data => {
    const { transactionId, referenceNo, amount, remark, error } = data;

    if (Platform.OS === "ios") {
      Alert.alert("Message", `${error}`, { cancelable: true });
    } else {
      ToastAndroid.show(`Message: ${error}`, ToastAndroid.LONG);
    }
  };

  pay = () => {
    try {
      const data = {};
      data.paymentId = "2"; // refer to ipay88 docs
      data.merchantKey = "{{ merchantKey }}";
      data.merchantCode = "{{ merchantCode }}";
      data.referenceNo = "1234565";
      data.amount = "1.00";
      data.currency = "MYR";
      data.productDescription = "Payment";
      data.userName = "test";
      data.userEmail = "test@gmail.com";
      data.userContact = "0123456789";
      data.remark = "me";
      data.utfLang = "UTF-8";
      data.country = "MY";
      data.backendUrl = "http://sample.com";
      const errs = Pay(data);
      if (Object.keys(errs).length > 0) {
        console.log(errs);
      }
    } catch (e) {
      console.log(e);
    }
  };

  render() {
    return (
      <View
        style={{
          flex: 1,
          justifyContent: "center",
          alignItems: "center"
        }}
      >
        <IPay88
          successNotify={this.successNotify}
          failedNotify={this.failedNotify}
          cancelNotify={this.cancelNotify}
        />
        <Button title="Pay" onPress={this.pay} />
      </View>
    );
  }
}
```

### Make Payment

// Refer to ipay88 docs for more info

* paymentId // optional
* merchantCode // required
* referenceNo // required
* amount // required
* currency // required
* productDescription // required
* userName // required
* userEmail // required
* remark // optional
* utfLang // optional
* country // required
* backendUrl // required

### Success Notify

* transactionId
* referenceNo
* amount
* remark
* authorizationCode

### Failed Notify

* transactionId
* referenceNo
* amount
* remark
* error

### Cancel Notify

* transactionId
* referenceNo
* amount
* remark
* error

### Maintained by [Virtual Spirit Technology Sdn Bhd](https://virtualspirit.me/)

Maintainer:
- [Rahmat Zulfikri](https://github.com/RZulfikri)

Any engineering support and project enquiry, kindly contact VirtualSpirit for any React Native projects.
