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
import com.balance.util.Notification;
import com.balance.util.UserInfo;
import com.balance.util.Utility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DAOImpl implements DAO {

    @Override
    public boolean checkUser(User user) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from users where username='" + user.getUsername() + "' and password='" + user.getPassword() + "' and active=1";
        boolean result = false;
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    result = true;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }
        return result;
    }

    @Override
    public boolean creatAccount(User user) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into users(username,password) values('" + user.getUsername() + "' , '" + user.getPassword() + "')";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public int find_UserId_ByUsernameAndPassword(User user) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int userId = 0;
        String sql = "SELECT id FROM users where username = '" + user.getUsername() + "' and password = '" + user.getPassword() + "' and active=1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    userId = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return userId;
    }

    @Override
    public boolean deleteAccount1(int userId) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update users set active=0 where id=" + userId + "";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public double getBalanceByUserId(int userId) {
        double balance = 0;
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select balance from balance where user_id = " + userId + "";
        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    balance = rs.getDouble("balance");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return balance;
    }

    @Override
    public boolean addIncomeCategory(IncomeCategory incomeCategory) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into income_category (category,user_id)values('" + incomeCategory.getCategory() + "','" + incomeCategory.getUser().getId() + "')";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public List<String> getIncomeCategories(int userId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> incomeCategoryList = new ArrayList<String>();
        String sql = "select category from income_category where user_id=" + userId + " and active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    incomeCategoryList.add(rs.getString("category"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return incomeCategoryList;
    }

    @Override
    public boolean addExpenseCategory(ExpenseCategory expenseCategory) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into expense_category (category,user_id)values('" + expenseCategory.getCategory() + "','" + expenseCategory.getUser().getId() + "')";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public List<String> getExpenseCategories(int userId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> expenseCategoryList = new ArrayList<String>();
        String sql = "select category from expense_category where user_id=" + userId + " and active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    expenseCategoryList.add(rs.getString("category"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return expenseCategoryList;
    }

    @Override
    public int find_IncomeCategoryId_ByName(String incomeCategoryName, int incomeUserId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int incomeCategoryId = 0;

        String sql = "select id from income_category where user_id = " + incomeUserId + " and category = '" + incomeCategoryName + "' and active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    incomeCategoryId = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return incomeCategoryId;
    }

    @Override
    public int find_ExpenseCategoryId_ByName(String expenseCategoryName, int expenseUserId) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int expenseCategoryId = 0;

        String sql = "select id from expense_category where user_id = " + expenseUserId + " and category = '" + expenseCategoryName + "' and active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    expenseCategoryId = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return expenseCategoryId;
    }

    @Override
    public boolean addIncome(Income income) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into income (amount , note , reg_date , user_id ,income_category_id) values (" + income.getAmount() + ", '" + income.getNote() + "' , '" + income.getReg_date() + "' , '" + UserInfo.userId + "' , '" + income.getIncomeCategory().getId() + "');";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public boolean addExpense(Expense expense) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into expense (amount , note , reg_date , user_id , "
                + "expense_category_id) values (" + expense.getAmount() + " , "
                + "'" + expense.getNote() + "' , '" + expense.getReg_date() + "' , "
                + "'" + expense.getUser().getId() + "' , '" + expense.getExpenseCategory().getId() + "')";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public boolean updateIncomeCategory(IncomeCategory incomeCategory) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update income_category set category = '" + incomeCategory.getCategory() + "'  where id = " + incomeCategory.getId() + "";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public boolean updateExpenseCategory(ExpenseCategory expenseCategory) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update expense_category set category = '" + expenseCategory.getCategory() + "'  where id = " + expenseCategory.getId() + "";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public int getİncomeCategoryById(String incomeCategory) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int incomeCategoryId = 0;
        String sql = "select id from income_category where category = '" + incomeCategory + "'";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    incomeCategoryId = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return incomeCategoryId;
    }

    @Override
    public int getExpenseCategoryById(String expenseCategory) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int incomeCategoryId = 0;
        String sql = "select id from expense_category where category = '" + expenseCategory + "'";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    incomeCategoryId = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return incomeCategoryId;
    }

    @Override
    public boolean deleteIncomeCategory(IncomeCategory incomeCategory) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update income_category set active = 0 where id = " + incomeCategory.getId() + "";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public boolean deleteExpenseCategory(ExpenseCategory expenseCategory) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "update expense_category set active = 0 where id = " + expenseCategory.getId() + "";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public ObservableList<IncomeTableView> getIncomes() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<IncomeTableView> incomeList = FXCollections.observableArrayList();
        String sql = "select i.id , i.amount , i.note , i.reg_date , ic.category from income i inner join income_category ic on ic.id = i.income_category_id and i.user_id = " + UserInfo.userId + " and active =1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    IncomeTableView incomeTableView = new IncomeTableView();
                    incomeTableView.setId(rs.getInt("i.id"));
                    incomeTableView.setAmount(rs.getDouble("i.amount"));
                    incomeTableView.setNote(rs.getString("i.note"));
                    incomeTableView.setIncomeCategory(rs.getString("ic.category"));
                    incomeTableView.setReg_date(rs.getString("i.reg_date"));
                    incomeList.add(incomeTableView);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return incomeList;
    }

    @Override
    public ObservableList<ExpenseTableView> getExpenses() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<ExpenseTableView> expenseList = FXCollections.observableArrayList();
        String sql = "select e.id , e.amount , e.note , e.reg_date , ec.category from expense e inner join expense_category ec on ec.id = e.expense_category_id and e.user_id = " + UserInfo.userId + " and active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ExpenseTableView expenseTableView = new ExpenseTableView();
                    expenseTableView.setId(rs.getInt("e.id"));
                    expenseTableView.setAmount(rs.getDouble("e.amount"));
                    expenseTableView.setNote(rs.getString("e.note"));
                    expenseTableView.setExpenseCategory(rs.getString("ec.category"));
                    expenseTableView.setReg_date(rs.getString("e.reg_date"));
                    expenseList.add(expenseTableView);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return expenseList;
    }

    @Override
    public ObservableList<IncomeTableView> searchIncome(String incomeBeginDate, String incomeEndDate) {

        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<IncomeTableView> incomeList = FXCollections.observableArrayList();
        String sql = "select i.id , i.amount , i.note , i.reg_date , ic.category from income i inner join income_category ic on ic.id = i.income_category_id where i.reg_date between '" + incomeBeginDate + "' and '" + incomeEndDate + "' and i.user_id = " + UserInfo.userId + " and ic.active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    IncomeTableView incomeTableView = new IncomeTableView();
                    incomeTableView.setId(rs.getInt("i.id"));
                    incomeTableView.setAmount(rs.getDouble("i.amount"));
                    incomeTableView.setNote(rs.getString("i.note"));
                    incomeTableView.setIncomeCategory(rs.getString("ic.category"));
                    incomeTableView.setReg_date(rs.getString("i.reg_date"));
                    incomeList.add(incomeTableView);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return incomeList;
    }

    @Override
    public ObservableList<ExpenseTableView> searchExpense(String expenseBeginDate, String expenseEndDate) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<ExpenseTableView> expenseList = FXCollections.observableArrayList();
        String sql = "select e.id , e.amount , e.note , e.reg_date , ec.category from expense e inner join expense_category ec on ec.id = e.expense_category_id where e.reg_date between '" + expenseBeginDate + "' and '" + expenseEndDate + "' and e.user_id = " + UserInfo.userId + " and ec.active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    ExpenseTableView expenseTableView = new ExpenseTableView();
                    expenseTableView.setId(rs.getInt("e.id"));
                    expenseTableView.setAmount(rs.getDouble("e.amount"));
                    expenseTableView.setNote(rs.getString("e.note"));
                    expenseTableView.setExpenseCategory(rs.getString("ec.category"));
                    expenseTableView.setReg_date(rs.getString("e.reg_date"));
                    expenseList.add(expenseTableView);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return expenseList;
    }

    @Override
    public ObservableList<String> getExpenseCategory() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<String> expenseCategoryList = FXCollections.observableArrayList();
        String sql = "select category from expense_category where user_id=" + UserInfo.userId + " and active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    expenseCategoryList.add(rs.getString("category"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return expenseCategoryList;
    }

    @Override
    public boolean addPlan(Plan plan) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into plan (amount,name,begin_date,end_date,user_id)values(" + plan.getAmount() + " , '" + plan.getName() + "' , '" + plan.getBegin_date() + "' , '" + plan.getEnd_date() + "' , " + UserInfo.userId + ")";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public int getPlanId(String planName) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int planId = 0;
        String sql = "select id from plan where name = '" + planName + "' and active = 1 and user_id = " + UserInfo.userId + "";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    planId = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return planId;
    }

    @Override
    public boolean addplanDetails(PlanDetails details) {
        Connection c = null;
        PreparedStatement ps = null;
        String sql = "insert into plan_details(category,amount,user_id,plan_id) values ('" + details.getCategory() + "' , " + details.getAmount() + " , " + UserInfo.userId + " ," + details.getPlan().getId() + ")";
        boolean result = false;

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.execute();
                result = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, null);
        }

        return result;
    }

    @Override
    public List<String> getPlans() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<String> plans = new ArrayList<String>();
        String sql = "select name from plan where user_id = " + UserInfo.userId + "";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    plans.add(rs.getString("name"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return plans;
    }

    @Override
    public int getPlanIdByName(String planName) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        int plan_id = 0;

        String sql = "select id from plan where name = '" + planName + "' and user_id = " + UserInfo.userId + "";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    plan_id = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return plan_id;
    }

    @Override
    public List<PlanDetails> getPlanDetailsByPlanId(int plan_Id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<PlanDetails> planDetailsList = new ArrayList<PlanDetails>();
        String sql = "select category , amount from plan_details where user_id = " + UserInfo.userId + " and plan_id = " + plan_Id + "";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    PlanDetails details = new PlanDetails();
                    details.setAmount(rs.getDouble("amount"));
                    details.setCategory(rs.getString("category"));
                    planDetailsList.add(details);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return planDetailsList;
    }

    @Override
    public Plan getPlanById(int plan_id) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Plan plan = new Plan();
        String sql = "select amount , begin_date , end_date from plan where id = " + plan_id + " and active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    plan.setAmount(rs.getDouble("amount"));
                    plan.setBegin_date(rs.getString("begin_date"));
                    plan.setEnd_date(rs.getString("end_date"));
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return plan;
    }

    @Override
    public int getExpenseCategoryId_byCategory(String categoryName) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int expenseCategoryId = 0;
        String sql = "select id from expense_category where category = '" + categoryName + "' and user_id = " + UserInfo.userId + " and active = 1";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    expenseCategoryId = rs.getInt("id");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return expenseCategoryId;
    }

    @Override
    public double findSumAmountExpense(int expenseCatId, String beginDate, String endDate) {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        double sumAmount = 0;
        String sql = "select sum(amount) from expense where user_id = " + UserInfo.userId + " and expense_category_id =" + expenseCatId + " and reg_date between '" + beginDate + "' and '" + endDate + "'";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    sumAmount = rs.getDouble("sum(amount)");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return sumAmount;
    }

    @Override
    public ObservableList<Income> fillIncomePieChart() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<Income> incomeList = FXCollections.observableArrayList();
        String sql = "select sum(i.amount) as sumAmount , ic.category as incomeCategory from income i inner join income_category ic on i.income_category_id = ic.id where i.user_id = " + UserInfo.userId + " and ic.active = 1 group by ic.category";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Income income = new Income();
                    income.setAmount(rs.getDouble("sumAmount"));
                    income.setNote(rs.getString("incomeCategory"));
                    incomeList.add(income);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return incomeList;

    }

    @Override
    public ObservableList<Expense> fillExpensePieChart() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ObservableList<Expense> expenseList = FXCollections.observableArrayList();
        String sql = "select sum(e.amount) as sumAmount , ec.category as expenseCategory from expense e inner join expense_category ec on e.expense_category_id = ec.id where e.user_id = " + UserInfo.userId + " and ec.active = 1 group by ec.category";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    Expense expense = new Expense();
                    expense.setAmount(rs.getDouble("sumAmount"));
                    expense.setNote(rs.getString("expenseCategory"));
                    expenseList.add(expense);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }
        return expenseList;
    }

    @Override
    public int sumIncomeAmount() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int income = 0;
        String sql = "select sum(amount) from income where user_id = " + UserInfo.userId + "";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    income = rs.getInt("sum(amount)");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return income;
    }

    @Override
    public int sumExpenseAmount() {
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int expense = 0;
        String sql = "select sum(amount) from expense where user_id = " + UserInfo.userId + " ";

        try {
            c = DBHelper.getConnection();
            if (c != null) {
                ps = c.prepareStatement(sql);
                rs = ps.executeQuery();
                if (rs.next()) {
                    expense = rs.getInt("sum(amount)");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            Utility.disconnect(c, ps, rs);
        }

        return expense;
    }
}
