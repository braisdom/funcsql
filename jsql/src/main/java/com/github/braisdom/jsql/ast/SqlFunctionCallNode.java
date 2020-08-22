package com.github.braisdom.jsql.ast;

import java.util.ArrayList;
import java.util.List;

public class SqlFunctionCallNode extends Aliasable implements Projectional, SqlFunctionOperand {
    private String name;
    private List<SqlFunctionOperand> operands = new ArrayList<>();

    public String getName() {
        return name;
    }

    public SqlFunctionCallNode setName(String name) {
        this.name = name;
        return this;
    }

    public List<SqlFunctionOperand> getOperands() {
        return operands;
    }

    public void addOperands(SqlFunctionOperand operand) {
        this.operands.add(operand);
    }
}