# react-native-community-geolocation

[![npm](https://img.shields.io/npm/v/react-native-community-geolocation)](https://www.npmjs.com/package/react-native-community-geolocation.svg)
![Supports Android](https://img.shields.io/badge/platforms-android-lightgrey.svg)
![MIT License](https://img.shields.io/npm/l/@react-native-community/geolocation.svg)

<!--[![npm total downloads](https://img.shields.io/npm/dt/react-native-community-geolocation.svg)](https://img.shields.io/npm/dt/react-native-community-geolocation.svg)
[![npm monthly downloads](https://img.shields.io/npm/dm/react-native-community-geolocation.svg)](https://img.shields.io/npm/dm/react-native-community-geolocation.svg)
[![npm weekly downloads](https://img.shields.io/npm/dw/react-native-community-geolocation.svg)](https://img.shields.io/npm/dw/react-native-community-geolocation.svg)<br>-->

Device Information for [React Native](https://github.com/facebook/react-native).<br>
Here's an image the example:<br>
<img src="https://media.giphy.com/media/dzJYhWnIIQzmT2YePT/giphy.gif"/>

Move along.

## Getting started

### Until now only Android

<img src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiA/PjwhRE9DVFlQRSBzdmcgIFBVQkxJQyAnLS8vVzNDLy9EVEQgU1ZHIDEuMC8vRU4nICAnaHR0cDovL3d3dy53My5vcmcvVFIvMjAwMS9SRUMtU1ZHLTIwMDEwOTA0L0RURC9zdmcxMC5kdGQnPjxzdmcgZW5hYmxlLWJhY2tncm91bmQ9Im5ldyAwIDAgMzIgMzIiIGhlaWdodD0iMzJweCIgaWQ9IkxheWVyXzEiIHZlcnNpb249IjEuMCIgdmlld0JveD0iMCAwIDMyIDMyIiB3aWR0aD0iMzJweCIgeG1sOnNwYWNlPSJwcmVzZXJ2ZSIgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB4bWxuczp4bGluaz0iaHR0cDovL3d3dy53My5vcmcvMTk5OS94bGluayI+PGc+PHBhdGggZD0iTTUsOC45NzhjLTEuMTA0LDAtMiwwLjg5Ni0yLDJ2OGMwLDEuMTA0LDAuODk2LDIsMiwyczItMC44OTYsMi0ydi04QzcsOS44NzMsNi4xMDQsOC45NzgsNSw4Ljk3OHoiIGZpbGw9IiM4NUM4MDgiLz48cGF0aCBkPSJNMjcsOC45NzhjLTEuMTA0LDAtMiwwLjg5Ni0yLDJ2OGMwLDEuMTA0LDAuODk2LDIsMiwyczItMC44OTYsMi0ydi04QzI5LDkuODczLDI4LjEwNCw4Ljk3OCwyNyw4Ljk3OHoiIGZpbGw9IiM4NUM4MDgiLz48cGF0aCBkPSJNOCwyNC45NzhoMnY1YzAsMS4xMDQsMC44OTYsMiwyLDJzMi0wLjg5NiwyLTJ2LTVoNHY1YzAsMS4xMDQsMC44OTYsMiwyLDJzMi0wLjg5NiwyLTJ2LTVoMnYtMTVIOFYyNC45Nzh6ICAgIiBmaWxsPSIjODVDODA4Ii8+PHBhdGggZD0iTTIwLjcyMywyLjUzbDEuMDI3LTEuNzc5YzAuMTM5LTAuMjQsMC4wNTctMC41NDUtMC4xODQtMC42ODRjLTAuMjM4LTAuMTM5LTAuNTQ1LTAuMDU3LTAuNjgzLDAuMTg0ICAgbC0xLjAwMSwxLjczNEMxOC43MzIsMS4zNDUsMTcuNDEsMC45NzgsMTYsMC45NzhjLTEuNDA5LDAtMi43MzEsMC4zNjctMy44ODIsMS4wMDhMMTEuMTE2LDAuMjUgICBjLTAuMTM5LTAuMjM5LTAuNDQzLTAuMzIxLTAuNjg0LTAuMTgzYy0wLjIzOCwwLjEzOC0wLjMyLDAuNDQzLTAuMTgzLDAuNjgzbDEuMDI4LDEuNzhDOS4yOTQsMy45ODYsOCw2LjMyOCw4LDguOTc4aDE2ICAgQzI0LDYuMzI4LDIyLjcwNiwzLjk4NiwyMC43MjMsMi41M3ogTTEzLDUuOTc4Yy0wLjU1MywwLTEtMC40NDctMS0xczAuNDQ3LTEsMS0xczEsMC40NDcsMSwxUzEzLjU1Myw1Ljk3OCwxMyw1Ljk3OHogTTE5LDUuOTc4ICAgYy0wLjU1MywwLTEtMC40NDctMS0xczAuNDQ3LTEsMS0xczEsMC40NDcsMSwxUzE5LjU1Myw1Ljk3OCwxOSw1Ljk3OHoiIGZpbGw9IiM4NUM4MDgiLz48L2c+PGcvPjxnLz48Zy8+PGcvPjxnLz48Zy8+PC9zdmc+"/>

### using yarn

`$ yarn add react-native-community-geolocation --save`

### using npm

`$ npm install react-native-community-geolocation --save`

### Mostly automatic installation react version < 0.6

`$ react-native link react-native-community-geolocation`

## Usage

```javascript
import React, { useState, useEffect } from "react";
import { StyleSheet, Text, View, Button } from "react-native";
import Geolocation from "react-native-community-geolocation";

export default function App() {
  const [status, setStatus] = useState("starting");
  const [message, setMessage] = useState("message");

  async function getGeolocation() {
    /*
     *until now options
     *provider disponibles:
     *gps (accuracy: ~= 3.41 (m))
     *network (accuracy:~= 14.44(m))
     */
    const options = { provider: "gps" || "network" };
    const position = await Geolocation.getCurrentPosition({ provider: "gps" });
    setMessage(position);
  }
  return (
    <View style={styles.container}>
      <Text style={styles.welcome}>☆Geolocation example☆</Text>
      <Text style={styles.instructions}>STATUS: {status}</Text>
      <Text style={styles.welcome}>☆NATIVE CALLBACK MESSAGE☆</Text>
      <Text style={styles.instructions}>{message}</Text>
      <Button title="Press" onPress={() => getGeolocation()} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: "center",
    alignItems: "center",
    backgroundColor: "#F5FCFF"
  },
  welcome: {
    fontSize: 20,
    textAlign: "center",
    margin: 10
  },
  instructions: {
    textAlign: "center",
    color: "#333333",
    marginBottom: 5
  }
});
```

## Method

| Name                 | Param    | Type     | values     |
| -------------------- | -------- | -------- | ---------- |
| `getCurrentPosition` | `option` | `object` | `provider` |

### Params

| Name      | Key        | Values                |
| --------- | ---------- | --------------------- |
| `options` | `provider` | network <b>or</b> gps |

as soon as possible I will study objective c for implementation thanks to everyone who uses ^^.
