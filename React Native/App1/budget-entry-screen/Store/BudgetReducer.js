import { ADD_BUDGET_ITEM } from './BudgetActions';

const initialState = {
  budgetEntries: [
    { name: 'Groceries', plannedAmount: 100, actualAmount: 90 },
    { name: 'Utilities', plannedAmount: 200, actualAmount: 190 },
    { name: 'Sports', plannedAmount: 300, actualAmount: 600 },
    { name: 'Education', plannedAmount: 2900, actualAmount: 1900 },
    { name: 'Food', plannedAmount: 300, actualAmount: 400 },
  ],
};


const budgetReducer = (state = initialState, action) => {
  switch (action.type) {
    case ADD_BUDGET_ITEM:
      return {
        ...state,
        budgetEntries: [...state.budgetEntries, action.payload],
      };
    default:
      return state;
  }
};

export default budgetReducer;
