import { StyleSheet } from 'react-native';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#2209',
    padding: 20,
    color: '#ffffff',
    paddingTop: 170
  },
  container1: {
    flex: 1,
    backgroundColor: '#2209',
    padding: 20,
    // color: '#ffffff',
    color: '#2209'
    // height: '500'
  },
  textIn: {
    borderWidth: 1,
    borderColor: '#cccccc',
    borderRadius: 5,
    marginBottom: 10,
    padding: 10,
    marginTop: 20,
    color: '#ffffff',
  },
  button: {
    backgroundColor: 'green',
    borderRadius: 5,
    padding: 15,
    alignItems: 'center',
    marginBottom:10,
  },
  buttonText: {
    color: '#ffffff',
    fontWeight: 'bold',
    fontSize: 16,
  },
  listItemContainer: {
    paddingBottom: 15,
    marginBottom: 10,
    borderBottomWidth: 1, // Apply border only at the bottom
    borderBottomColor: '#ffffff', // Border color white
    alignContent: 'center',
    justifyContent: 'center'
  },
  listItemName: {
    fontWeight: 'bold',
    marginBottom: 5,
    fontSize: 25,
    color: '#fff',
    textAlign: 'center'
  },
  amt: {
    fontSize: 17 ,
    color: 'white' , 
    marginBottom: 3 , 
    padding: 5 , 
    borderRadius: 11, 
    textAlign: 'center'
  },
  footer: {
    position: 'absolute',
    bottom: 0,
    left: 0,
    right: 0,
    backgroundColor: '#fff', // Background color of the footer
    paddingHorizontal: 20, // Horizontal padding
    paddingVertical: 10, // Vertical padding
  },
});
