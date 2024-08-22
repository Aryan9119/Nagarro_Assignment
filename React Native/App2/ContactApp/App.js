import React, {useEffect, useState } from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createStackNavigator } from '@react-navigation/stack';
import { createDrawerNavigator } from '@react-navigation/drawer';
import ContactListScreen from './screens/ContactListScreen';
import AddContactScreen from './screens/AddContactScreen';
import EditContactScreen from './screens/EditContactScreen';
import FavoriteContactsScreen from './screens/FavoriteContactsScreen';
import * as ImagePicker from 'expo-image-picker';

const Stack = createStackNavigator();
const Drawer = createDrawerNavigator();

const ContactStack = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="ContactListScreen" component={ContactListScreen} options={{ headerShown: false }}/>
      <Stack.Screen name="AddContactScreen" component={AddContactScreen} options={{ title: 'Add Contact' }} />
      <Stack.Screen name="EditContactScreen" component={EditContactScreen} options={{ title: 'Edit Contact' }} />
    </Stack.Navigator>
  );
};

const FavoriteStack = () => {
  return (
    <Stack.Navigator>
      <Stack.Screen name="FavoriteContactsScreen" component={FavoriteContactsScreen} options={{ headerShown: false }} />
    </Stack.Navigator>
  );
};


  export default function App() {

    const [hasGalleryPermission, setHasGalleryPermission] = useState(null);

    useEffect(() => {
      (async () => {
        const galleryStatus = await ImagePicker.requestMediaLibraryPermissionsAsync();
        setHasGalleryPermission(galleryStatus.status === 'granted');
      })();
    }, []);

    if (hasGalleryPermission === false) {
      Alert.alert(
        'Permission required',
        'You need to grant permission to access your photo library to pick an image.',
        [{ text: 'OK' }]
      );
      return null;
    }

    return (
      <NavigationContainer>
        <Drawer.Navigator initialRouteName="Contacts">
          <Drawer.Screen name="Contacts" component={ContactStack} />
          <Drawer.Screen name="Favorites" component={FavoriteStack} />
        </Drawer.Navigator>
      </NavigationContainer>
    );
  }