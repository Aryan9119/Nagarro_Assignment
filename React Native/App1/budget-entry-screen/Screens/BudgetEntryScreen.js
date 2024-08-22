import React, { useState } from 'react';
import { View, TextInput, TouchableOpacity, Text, TouchableWithoutFeedback, Keyboard, Alert } from 'react-native';
import { useDispatch } from 'react-redux';
import { addBudgetItem } from '../Store/BudgetActions';
import { useNavigation } from '@react-navigation/native';
import { styles } from './styles';
import * as yup from 'yup';

const BudgetEntryScreen = () => {
  const [name, setName] = useState('');
  const [plannedAmount, setPlannedAmount] = useState('');
  const [actualAmount, setActualAmount] = useState('');
  const dispatch = useDispatch();
  const navigation = useNavigation();

  const validation = yup.object().shape({
    name: yup.string().trim().required('Name is required'),
    plannedAmount: yup
      .string()
      .matches(/^\d*\.?\d*$/, 'Planned amount must be a valid number')
      .required('Planned amount is required'),
    actualAmount: yup
      .string()
      .matches(/^\d*\.?\d*$/, 'Actual amount must be a valid number')
      .required('Actual amount is required'),
  });
  const handleSave = async () => {
    try {
      await validation.validate({
        name,
        plannedAmount,
        actualAmount
      });

      const item = { name, plannedAmount, actualAmount };
      dispatch(addBudgetItem(item));
      setName('');
      setPlannedAmount('');
      setActualAmount('');
      navigation.navigate('BudgetEntryListing');
    } catch (error) {
      Alert.alert('Validation Error', error.message);
    }
  };

  const navigateToShowList = () => {
    navigation.navigate('BudgetEntryListing');
  }

  return (
    <TouchableWithoutFeedback onPress={() => Keyboard.dismiss()}>
      <View style={styles.container}>
        <TextInput
          style={styles.textIn}
          placeholder="Name"
          value={name}
          onChangeText={setName}
        />
        <TextInput
          style={styles.textIn}
          placeholder="Planned Amount"
          value={plannedAmount}
          onChangeText={setPlannedAmount}
          keyboardType="numeric"
        />
        <TextInput
          style={styles.textIn}
          placeholder="Actual Amount"
          value={actualAmount}
          onChangeText={setActualAmount}
          keyboardType="numeric"
        />
        <TouchableOpacity style={styles.button} onPress={handleSave}>
          <Text style={styles.buttonText}>Save</Text>
        </TouchableOpacity>
        <TouchableOpacity style={styles.button} onPress={navigateToShowList}>
          <Text style={styles.buttonText}>Show Items</Text>
        </TouchableOpacity>
      </View>
    </TouchableWithoutFeedback>
  );
};

export default BudgetEntryScreen;
