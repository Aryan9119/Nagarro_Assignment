import React, { useState } from 'react';
import { View, Text, TouchableOpacity, FlatList, Alert, Image, TextInput } from 'react-native';
import { useNavigation, useFocusEffect } from '@react-navigation/native';
import { Swipeable } from 'react-native-gesture-handler';
import * as SQLite from 'expo-sqlite';
import globalStyle from '../styles/globalStyle.js';
import deleteUser from '../utils/deleteUser';
import getData from '../utils/getData';
import toggleFavorite from '../utils/toggleFavorite';

let db = SQLite.openDatabase('Contact2107.db');

const ContactListScreen = () => {
  const navigation = useNavigation();
  const [userList, setUserList] = useState([]);
  const [searchQuery, setSearchQuery] = useState('');

  useFocusEffect(
    React.useCallback(() => {
      fetchData();
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

  const leftSwipe = (item) => {
    return (
      <View style={globalStyle.swipeContainer}>
        <TouchableOpacity style={globalStyle.belowView} onPress={() => handleToggleFavorite(item.user_id, item.is_favorite)}>
          <Image source={item.is_favorite ? require('../images/favorite.png') : require('../images/not_favorite.png')} style={globalStyle.icons} />
        </TouchableOpacity>
      </View>
    );
  };

  const fetchData = () => {
    getData(
      (data) => {
        setUserList(data);
      },
      (error) => {
        console.error('Failed to fetch data:', error);
      }
    );
  };
  
  const handleToggleFavorite = (id, isFavorite) => {
    toggleFavorite(
      id,
      isFavorite,
      () => {
        Alert.alert(
          'Success',
          'Updated successfully',
          [
            {
              text: 'Ok',
              onPress: () => {
                fetchData();
              },
            },
          ],
          { cancelable: false },
        );
      },
      () => {
        console.error('Failed to update favorite status');
      }
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
              fetchData();
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

  const handleSearch = (text) => {
    setSearchQuery(text);
    if (text) {
      const filteredList = userList.filter(item => {
        return item.name.toLowerCase().includes(text.toLowerCase());
      });
      setUserList(filteredList);
    } else {
      fetchData();
    }
  };

  return (
    <View style={globalStyle.container}>
      <View style={globalStyle.searchContainer}>
        <TextInput
          style={globalStyle.searchBox}
          placeholder="Search Contacts"
          value={searchQuery}
          onChangeText={handleSearch}
        />
      </View>
      <FlatList
        data={userList}
        renderItem={({ item, index }) => {
          return (
            <Swipeable renderRightActions={() => rightSwipe(item)} renderLeftActions={() =>leftSwipe(item)}>
              <TouchableOpacity style={globalStyle.userItem} onPress={() => navigation.navigate('EditContactScreen', { data: { name: item.name, mobile: item.mobile, telephone: item.telephone, image: item.image, id: item.user_id } })}>
                {item.image ? (
                  <Image source={{ uri: item.image }} style={globalStyle.icons} />
                ) : (
                  <Image source={require('../images/contact.png')} style={globalStyle.icons} />
                )}
                <Text style={globalStyle.itemText}>{item.name}</Text>
              </TouchableOpacity>
            </Swipeable>
          );
        }}
        keyExtractor={item => item.user_id.toString()}
      />
      <TouchableOpacity style={globalStyle.addNewBtn} onPress={() => navigation.navigate('AddContactScreen')}>
        <Text style={globalStyle.btnText}>Add</Text>
      </TouchableOpacity>
    </View>
  );
};

export default ContactListScreen;
