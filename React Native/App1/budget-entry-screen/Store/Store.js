import { createStore } from 'redux';
import rootReducer from './BudgetReducer';

const store = createStore(rootReducer);

export default store;
