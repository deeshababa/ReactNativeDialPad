/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';

import {
 StyleSheet,
  View,
  Button,
  NativeModules
} from 'react-native';

import {
  Colors,
  DebugInstructions,
  Header,
  LearnMoreLinks,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';

// const Section = ({children, title}): Node => {
//   const isDarkMode = useColorScheme() === 'dark';
//   return (
//     <View style={styles.sectionContainer}>
//       <Text
//         style={[
//           styles.sectionTitle,
//           {
//             color: isDarkMode ? Colors.white : Colors.black,
//           },
//         ]}>
//         {title}
//       </Text>
//       <Text
//         style={[
//           styles.sectionDescription,
//           {
//             color: isDarkMode ? Colors.light : Colors.dark,
//           },
//         ]}>
//         {children}
//       </Text>
//     </View>
//   );
// };

const App: () => Node = () => {
  // const isDarkMode = useColorScheme() === 'dark';
  const { CalendarManager, Counter } = NativeModules;



  // const backgroundStyle = {
  //   backgroundColor: isDarkMode ? Colors.darker : Colors.lighter,
  // };
  const increment=(number)=> {
    console.log(Counter.increment(number));
  }

  const decrement=()=> {
    Counter.decrement()
      .then(message => console.log(message))
      .catch(error => console.error(error));
  }
  const getCount=()=> {
    Counter.getCount(count =>console.log({ count }));
  }

  return (
    <View style={styles.container}>
    
    <Button title={'Click'} onPress={()=>increment('99567845')} />
    {/* <Button title={'+'} onPress={increment} />
     <Button title={'Get Count'} onPress={getCount} /> */}
    {/* <Button title={'Add Event'} onPress={addEvent} />  */}
  </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});


export default App;
