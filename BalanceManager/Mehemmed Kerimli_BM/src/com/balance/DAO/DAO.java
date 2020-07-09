package com.balance.DAO;

import com.balance.model.Expense;
import com.balance.model.ExpenseCategory;
import com.balance.model.ExpenseTableView;
import com.balance.model.Income;
import com.balance.model.IncomeCategory;
import com.balance.model.IncomeTableView;
import com.balance.model.Plan;
import com.balance.model.PlanDetails;
import com.balance.model.User;
import java.util.List;
import javafx.collections.ObservableList;

public interface DAO {

    public boolean checkUser(User user);

    public boolean creatAccount(User user);

    public int find_UserId_ByUsernameAndPassword(User user);

    public boolean deleteAccount1(int userId);

    public double getBalanceByUserId(int userId);

    public boolean addIncomeCategory(IncomeCategory incomeCategory);

    public List<String> getIncomeCategories(int userId);

    public boolean addExpenseCategory(ExpenseCategory expenseCategory);

    public List<String> getExpenseCategories(int userId);

    public int find_IncomeCategoryId_ByName(String incomeCategoryName, int incomeUserId);

    public int find_ExpenseCategoryId_ByName(String expenseCategoryName, int expenseUserId);

    public boolean addIncome(Income income);

    public boolean addExpense(Expense expense);

    public boolean updateIncomeCategory(IncomeCategory incomeCategory);

    public boolean updateExpenseCategory(ExpenseCategory expenseCategory);

    public int getİncomeCategoryById(String incomeCategory);

    public int getExpenseCategoryById(String expenseCategory);

    public boolean deleteIncomeCategory(IncomeCategory incomeCategory);

    public boolean deleteExpenseCategory(ExpenseCategory expenseCategory);

    public ObservableList<IncomeTableView> getIncomes();

    public ObservableList<ExpenseTableView> getExpenses();

    public ObservableList<IncomeTableView> searchIncome(String incomeBeginDate, String incomeEndDate);

    public ObservableList<ExpenseTableView> searchExpense(String expenseBeginDate, String expenseEndDate);

    public ObservableList<String> getExpenseCategory();

    public boolean addPlan(Plan plan);

    public int getPlanId(String planName);

    public boolean addplanDetails(PlanDetails details);

    public List<String> getPlans();

    public int getPlanIdByName(String planName);

    public List<PlanDetails> getPlanDetailsByPlanId(int plan_Id);

    public Plan getPlanById(int plan_id);

    public int getExpenseCategoryId_byCategory(String categoryName);

    public double findSumAmountExpense(int expenseCatId, String beginDate, String endDate);

    public ObservableList<Income> fillIncomePieChart();

    public ObservableList<Expense> fillExpensePieChart();

    public int sumIncomeAmount();

    public int sumExpenseAmount();
}
