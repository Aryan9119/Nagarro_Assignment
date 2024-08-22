import { StyleSheet } from 'react-native';

export default StyleSheet.create({

  container1: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  input: {
    width: '80%',
    height: 50,
    borderRadius: 10,
    borderWidth: 0.3,
    paddingLeft: 20,
    marginBottom: 20,
  },
  addBtn: {
    backgroundColor: 'purple',
    width: '80%',
    height: 50,
    borderRadius: 10,
    justifyContent: 'center',
    alignItems: 'center',
  },
  addBt: {
    justifyContent: 'center',
    alignItems: 'center',
    marginBottom: 90,
    height:30,
    width: 24
  },
  icons1: {
    height:45,
    width: 45
  },

  container: {
    flex: 1,
  },
  addNewBtn: {
    backgroundColor: 'purple',
    width: 60,
    height: 50,
    borderRadius: 20,
    position: 'absolute',
    bottom: 20,
    right: 20,
    justifyContent: 'center',
    alignItems: 'center',
  },
  btnText: {
    color: '#fff',
    fontSize: 18,
  },
  swipeContainer: {
    alignItems: 'flex-end',
    justifyContent: 'center',
    paddingRight: 10,
    width: 40,
  },
  belowView: {
    flexDirection: 'row',
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: '#f2f2f2',
    borderRadius: 10,
    height: 50,
  },
  userItem: {
    width: '100%',
    backgroundColor: '#fff',
    padding: 10,
    height: 60,
    borderColor: '#000000',
    borderBottomWidth: 1,
    flexDirection: 'row',
  },
  itemText: {
    fontSize: 20,
    fontWeight: '600',
    color: '#000',
    padding: 2
  },
  icons: {
    width: 24,
    height: 24,
    padding: 15
  },
  searchBox: {
    height: 40,
    borderWidth: 1,
    borderColor: 'gray',
    paddingLeft: 10,
    margin: 10,
  },
  cancelButton: {
    paddingHorizontal: 10,
    paddingVertical: 5,
  },
  cancelButtonText: {
    color: 'blue',
    fontWeight: 'bold',
  },
});
