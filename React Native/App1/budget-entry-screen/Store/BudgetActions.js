export const ADD_BUDGET_ITEM = 'ADD_BUDGET_ITEM';

export const addBudgetItem = (item) => ({
  type: ADD_BUDGET_ITEM,
  payload: item,
});
