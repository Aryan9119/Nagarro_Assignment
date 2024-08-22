import React from 'react';
import { View, Text, TouchableOpacity, ScrollView } from 'react-native';
import { useSelector } from 'react-redux';
import { styles } from './styles';
import { useNavigation } from '@react-navigation/native';

const BudgetEntryListingScreen = () => {
  const budgetEntries = useSelector(state => state.budgetEntries);
  const navigation = useNavigation();

  const navigateToEnterBudget = () => {
    navigation.navigate('BudgetEntry');
  }

  return (
    <ScrollView >
      <View style={[styles.container1]}>
        {budgetEntries.map((data, index) => (
          <View key={index} style={styles.listItemContainer}>
            <Text style={[styles.listItemName]}>{data.name}</Text>
            <Text style={[styles.amt]}>Planned Amount: {data.plannedAmount}</Text>
            <Text style={[styles.amt]}>Actual Amount: {data.actualAmount}</Text>
            {index < budgetEntries.length - 1 && <View style={styles.border} />}
          </View>
        ))}
        <TouchableOpacity style={[styles.button]} onPress={navigateToEnterBudget}>
          <Text style={styles.buttonText}>Add Items</Text>
        </TouchableOpacity>
      </View>
    </ScrollView>
  );
};

export default BudgetEntryListingScreen;
