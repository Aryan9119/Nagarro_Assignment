import * as SQLite from 'expo-sqlite';

let db = SQLite.openDatabase('Contact2107.db');

const deleteUser = (id, onSuccess, onError) => {
  db.transaction(function(txn) {
    txn.executeSql(
      'DELETE FROM contacts where user_id=?',
      [id],
      (tx, results) => {
        if (results.rowsAffected > 0) {
          onSuccess();
        } else {
          onError();
        }
      },
    );
  });
};

export default deleteUser;
