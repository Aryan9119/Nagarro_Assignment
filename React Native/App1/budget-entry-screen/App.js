import React from 'react';
import { View, Text, StyleSheet } from 'react-native';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import BudgetEntryListingScreen from './Screens/BudgetEntryListingScreen';
import BudgetEntryScreen from './Screens/BudgetEntryScreen';
import { Provider } from 'react-redux';
import store from './Store/Store';
import 'react-native-gesture-handler';


const Stack = createStackNavigator();

const design = StyleSheet.create({
  container: {
    flex: 1,
  },
});

export default function App() {
  return (
    <Provider store={store} >
      <View style={design.container}>
      <NavigationContainer>
        <Stack.Navigator initialRouteName="Budget Entry List">
           <Stack.Screen name="BudgetEntry" component={BudgetEntryScreen} options={{ title: "Budget Entry" }} />
           <Stack.Screen name="BudgetEntryListing" component={BudgetEntryListingScreen} options={{ title: 'Budget Entry Listing'}} />
        </Stack.Navigator>
      </NavigationContainer>
      </View>
    </Provider>
  );
}




