import * as SQLite from 'expo-sqlite';

let db = SQLite.openDatabase('Contact2107.db');

const toggleFavorite = (id, isFavorite, onSuccess, onError) => {
  const newValue = isFavorite ? 0 : 1;
  db.transaction(txn => {
    txn.executeSql(
      'UPDATE contacts SET is_favorite = ? WHERE user_id = ?',
      [newValue, id],
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

export default toggleFavorite;
