import React, { useState } from 'react';
import { View, Text, FlatList, Image, Alert } from 'react-native';
import { useNavigation, useFocusEffect } from '@react-navigation/native';
import * as SQLite from 'expo-sqlite';
import { TouchableOpacity } from 'react-native-gesture-handler';
import globalStyle from '../styles/globalStyle.js';
import { Swipeable } from 'react-native-gesture-handler';
import deleteUser from '../utils/deleteUser';

let db = SQLite.openDatabase('Contact2107.db');

const FavoriteContactsScreen = () => {
  const navigation = useNavigation();
  const [favoriteContacts, setFavoriteContacts] = useState([]);

  useFocusEffect(
    React.useCallback(() => {
      getFavoriteContacts();
    }, [])
  )

  const rightSwipe = (item) => {
    return (
      <View style={globalStyle.swipeContainer}>
        <TouchableOpacity style={globalStyle.belowView} onPress={() => handleDeleteUser(item.user_id)}>
          <Image source={require('../images/delete.png')} style={globalStyle.icons} />
        </TouchableOpacity>
      </View>
    );
  };

  const handleDeleteUser = (id) => {
    deleteUser(id, () => {
      Alert.alert(
        'Success',
        'User deleted successfully',
        [
          {
            text: 'Ok',
            onPress: () => {
              getFavoriteContacts();
            },
          },
        ],
        { cancelable: false },
      );
    }, () => {
      Alert.alert(
        'Error',
        'Failed to delete user',
        [{ text: 'Ok', onPress: () => {} }],
        { cancelable: false },
      );
    });
  };

  const getFavoriteContacts = () => {
    db.transaction(
      tx => {
        tx.executeSql(
          'SELECT * FROM contacts WHERE is_favorite = 1 ORDER BY name COLLATE NOCASE ASC',
          [],
          (_, { rows }) => {
            setFavoriteContacts(rows._array);
          },
          (_, error) => {
            console.error('Error while fetching favorite contacts:', error);
          }
        );
      },
      error => {
        console.error('Transaction error:', error);
      }
    );
  };
  
  return (
    <View style={globalStyle.container}>
      <FlatList
        data={favoriteContacts}
        renderItem={({ item }) => (
          <Swipeable renderRightActions={() => rightSwipe(item)} >
          <TouchableOpacity style={globalStyle.userItem} onPress={() => navigation.navigate('EditContactScreen', { data: { name: item.name, mobile: item.mobile, telephone: item.telephone, image: item.image, id: item.user_id } })}>
                {item.image ? (
                  <Image source={{ uri: item.image }} style={globalStyle.icons} />
                ) : (
                  <Image source={require('../images/contact.png')} style={globalStyle.icons} />
                )}
                <Text style={globalStyle.itemText}>{item.name}</Text>
          </TouchableOpacity>
          </Swipeable>
        )}
        keyExtractor={item => item.user_id.toString()}
      />
    </View>
  );
};

export default FavoriteContactsScreen;

