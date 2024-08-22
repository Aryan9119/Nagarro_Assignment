import React, {useEffect, useState } from 'react';
import { View, Text, TextInput, TouchableOpacity, Alert, Image, TouchableWithoutFeedback, Keyboard } from 'react-native';
import { useNavigation } from '@react-navigation/native';
import * as SQLite from 'expo-sqlite';
import { pickImage } from '../utils/imagePicker';
import { contactSchema } from '../utils/validation';
import globalStyle from '../styles/globalStyle.js';

let db = SQLite.openDatabase('Contact2107.db');

const AddContactScreen = () => {
  const navigation = useNavigation();
  const [name, setName] = useState('');
  const [mobile, setMobile] = useState('');
  const [telephone, setTelephone] = useState('');
  const [image, setImage] = useState(null);
 
  const saveContact = () => {
    console.log(name, mobile, telephone, image);
    db.transaction(tx => {
      tx.executeSql(
        'INSERT INTO contacts (name, mobile, telephone, image, is_favorite) VALUES (?,?,?,?,?)',
        [name, mobile, telephone, image, 0],
        (tx, results) => {
          console.log('Results', results.rowsAffected);
          if (results.rowsAffected > 0) {
            Alert.alert(
              'Success',
              'You are Registered Successfully',
              [
                {
                  text: 'Ok',
                  onPress: () => navigation.navigate('ContactListScreen'),
                },
              ],
              {cancelable: false},
            );
          } else alert('Registration Failed');
        },
        error => {
          console.log(error);
        },
      );
    });
  };
 
  const handleSave = async () => {
    try {
      await contactSchema.validate({ name, mobile, telephone });
      saveContact();
    } catch (error) {
      Alert.alert('Validation Error', error.message);
    }
  };

  useEffect(() => {
    console.log('usefeect called');
    db.transaction(txn => {
      txn.executeSql(
        "SELECT name FROM sqlite_master WHERE type='table' AND name='contacts'",
        [],
        (tx, res) => {
          if (res.rows.length == 0) {
            txn.executeSql(
              'CREATE TABLE IF NOT EXISTS contacts(user_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR(20), mobile INTEGER, telephone INTEGER, image TEXT, is_favorite INTEGER)',
              [],
              (tpx, createResult) => {
                if (createResult.rowsAffected > 0) {
                  console.log('Table created successfully');
                } else {
                  console.log('Failed to create table');
                }
              },
              (_, error) => {
                console.log('Error creating table:', error);
              }
            );
          }
        },
        (_, error) => {
          console.log('Error while checking table existence:', error);
        }
      );
    });
  }, []);

  return (

    <TouchableWithoutFeedback onPress={() =>{
      Keyboard.dismiss();
      }}>
    <View style={globalStyle.container1}>
      
      <TouchableOpacity
        style={globalStyle.addBt}
        onPress={() => {
          pickImage().then(uri => {
            if (uri) {
              setImage(uri);
            }
          });
        }}
      >
        <Image source={require('../images/gallery.png')} style={globalStyle.icons1} />
      </TouchableOpacity>
      
    
      <TextInput
        placeholder="Enter User Name"
        style={globalStyle.input}
        value={name}
        onChangeText={txt => setName(txt)}
      />
      <TextInput
        placeholder="Enter User Mobile Number"
        value={mobile}
        onChangeText={txt => setMobile(txt)}
        style={[globalStyle.input, { marginTop: 20 }]}
      />
      <TextInput
        placeholder="Enter User Telephone Number"
        value={telephone}
        onChangeText={txt => setTelephone(txt)}
        style={[globalStyle.input, { marginTop: 20 }]}
      />
      
      <TouchableOpacity
        style={globalStyle.addBtn}
        onPress={() => {
          handleSave();
        }}
        >
        <Text style={globalStyle.btnText}>Save Contact</Text>
      </TouchableOpacity>
      
      
    </View>
    </TouchableWithoutFeedback>
    
  );
};

export default AddContactScreen;

