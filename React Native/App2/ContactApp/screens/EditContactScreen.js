import {View, Text, TextInput, TouchableOpacity, Alert, Image, TouchableWithoutFeedback, Keyboard} from 'react-native';
import React, {useEffect, useState} from 'react';
import {useNavigation, useRoute} from '@react-navigation/native';
import * as SQLite from 'expo-sqlite';
import { pickImage } from '../utils/imagePicker';
import { contactSchema } from '../utils/validation';
import globalStyle from '../styles/globalStyle.js';

const db = SQLite.openDatabase('Contact2107.db');
const EditContactScreen = () => {
  const route = useRoute();
  const navigation = useNavigation();
  const [name, setName] = useState('');
  const [mobile, setMobile] = useState(route.params.data.mobile);
  const [telephone, setTelephone] = useState(route.params.data.telephone);
  const [image, setImage] = useState(route.params.data.image);

  const updateContact = () => {
    db.transaction(function(txn) {
      txn.executeSql(
        'UPDATE contacts set name=?, mobile=? , telephone=?, image=? where user_id=?',
        [name, mobile, telephone, image, route.params.data.id],
        (tx, results) => {
          console.log('Results', results.rowsAffected);
          if (results.rowsAffected > 0) {
            Alert.alert(
              'Success',
              'Contact updated successfully',
              [
                {
                  text: 'Ok',
                  onPress: () => navigation.navigate('ContactListScreen'),
                },
              ],
              {cancelable: false},
            );
          } else alert('Updation Failed');
        },
      );
    });
  };

  const handleUpdate = async () => {
    try {
      await contactSchema.validate({ name, mobile, telephone });
      updateContact();
    } catch (error) {
      Alert.alert('Validation Error', error.message);
    }
  };

  useEffect(() => {
    setName(route.params.data.name);
    setMobile(route.params.data.mobile);
    setTelephone(route.params.data.telephone);
    setImage(route.params.data.image);
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
        <Image source={require('../images/gallery.png')} style={globalStyle.icons} />
      </TouchableOpacity>

      <TextInput

        placeholder="Enter User Name"
        style={globalStyle.input}
        value={name}
        onChangeText={txt => setName(txt)}
      />
      <TextInput
        
        placeholder="Enter User Mobile Number"
        value={mobile.toString()}
        onChangeText={txt => setMobile(txt)}
        style={[globalStyle.input, {marginTop: 20}]}
      />
      <TextInput
        placeholder="Enter User Telephone Number"
        value={telephone.toString()}
        onChangeText={txt => setTelephone(txt)}
        style={[globalStyle.input, {marginTop: 20}]}
      />
      <TouchableOpacity
        style={globalStyle.addBtn}
        onPress={() => {
          handleUpdate();
        }}
        >
        <Text style={globalStyle.btnText}>Save Contact</Text>
      </TouchableOpacity>
    </View>
    </TouchableWithoutFeedback>
  );
};

export default EditContactScreen;
