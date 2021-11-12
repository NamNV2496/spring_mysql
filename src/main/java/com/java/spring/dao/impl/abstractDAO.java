package com.java.spring.dao.impl;

import com.java.spring.dao.generic.IGenericDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class abstractDAO<T> implements IGenericDAO<T> {
    @Autowired
    private Environment env;

    @Override
    public <T> List<T> query(String sql, RowMapper<T> rowMapper, Object... parameters) {
        List<T> results = new ArrayList<>();
        Connection connection = null;
        PreparedStatement psStatemet = null;
        ResultSet resultSet = null;
        try {
            connection = getConnection(); // create a connection
            psStatemet = connection.prepareStatement(sql); //Creates a PreparedStatement object for sending parameterized SQL statements to the database.
            setParameter(psStatemet, parameters); // inset param of SQL command
            resultSet = psStatemet.executeQuery(); // execute sql command and get result to resuleSet
            int count = 0;
            while (resultSet.next()) {
                count++;
                results.add(rowMapper.mapRow(resultSet, count));
            }
            return results;
        } catch (SQLException e) {
            return null;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (psStatemet != null) {
                    psStatemet.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return null;
            }
        }
    }

    private Connection getConnection() {
        try {
            // get properties from application.properties
//            String driver = env.getProperty("spring.datasource.driver-class-name");
            String url = env.getProperty("spring.datasource.url");
            String user = env.getProperty("spring.datasource.username");
            String password = env.getProperty("spring.datasource.password");
//            Class.forName(driver); // load driver. Before we need it. but for now, it's automotive
            return DriverManager.getConnection(url, user, password); // return a connection
        } catch (Exception e) {
            return null;
        }

    }

    private void setParameter(PreparedStatement psStatemet, Object[] parameters) throws SQLException {
        for (int i = 0; i<parameters.length; i++) {
            Object param = parameters[i];
            System.out.println(String.format("setparam %d", i));
            if (param instanceof Integer) {
                psStatemet.setInt(i, (Integer) param);
            } else if (param instanceof String) {
                psStatemet.setString(i, (String) param);
            }
        }
    }


    @Override
    public void update(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);    // turn off auto commit to keep transaction
            statement = connection.prepareStatement(sql);   //Creates a PreparedStatement object for sending parameterized SQL statements to the database.
            setParameter(statement, parameters);            // inset param of SQL command
            statement.executeUpdate();                      // execute SQL command
            connection.commit();                            // commit if query success
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();                  // rollback to all if query fail
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
    }

    @Override
    public int insert(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            System.out.println(String.format("param length %d", parameters.length));
            int id = 0;
            connection = getConnection();
            connection.setAutoCommit(false);        // turn off auto commit to keep transaction
            statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);//Creates a PreparedStatement object for sending parameterized SQL statements to the database.
            setParameter(statement, parameters);    // inset param of SQL command
            statement.executeUpdate();              // execute SQL command
            resultSet = statement.getGeneratedKeys();   // get key to check successfully of query command
            if (resultSet.next()) {
                id = resultSet.getInt(1);
                System.out.println(String.format("id = %d", id));
            }
            connection.commit();                  // commit if query success
            return id;
        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();          // rollback to all if query fail
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e2) {
                e2.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int count(String sql, Object... parameters) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            int count = 0;
            connection = getConnection();
            statement = connection.prepareStatement(sql);
            setParameter(statement, parameters);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt(1);
            }
            return count;
        } catch (SQLException e) {
            return 0;
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException e) {
                return 0;
            }
        }
    }
}
