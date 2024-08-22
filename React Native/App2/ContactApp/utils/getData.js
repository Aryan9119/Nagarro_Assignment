import * as SQLite from 'expo-sqlite';

let db = SQLite.openDatabase('Contact2107.db');

const getData = (onSuccess, onError) => {
  const maxRetries = 3;
  let retries = 0;

  const executeQuery = () => {
    setTimeout(() => {
      db.transaction(tx => {
        tx.executeSql(
          'SELECT * FROM contacts ORDER BY name COLLATE NOCASE ASC',
          [],
          (_, { rows }) => {
            onSuccess(rows._array);
          },
          (_, error) => {
            console.error('Error while fetching data:', error);
            if (error.code === 'database is locked' && retries < maxRetries) {
              retries++;
              console.log(`Retrying query. Retry attempt ${retries}/${maxRetries}`);
              executeQuery();
            } else {
              onError(error);
            }
          }
        );
      });
    }, 2000);
  };

  executeQuery();
};

export default getData;
