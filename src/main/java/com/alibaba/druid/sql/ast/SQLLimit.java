/*
 * Copyright 1999-2101 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.alibaba.druid.sql.ast;

import com.alibaba.druid.sql.dialect.mysql.visitor.MySqlASTVisitor;
import com.alibaba.druid.sql.visitor.SQLASTVisitor;

/**
 * Created by wenshao on 16/9/25.
 */
public class SQLLimit extends SQLObjectImpl {

    public SQLLimit() {

    }

    public SQLLimit(SQLExpr rowCount) {
        this.setRowCount(rowCount);
    }

    private SQLExpr rowCount;
    private SQLExpr offset;

    public SQLExpr getRowCount() {
        return rowCount;
    }

    public void setRowCount(SQLExpr rowCount) {
        if (rowCount != null) {
            rowCount.setParent(this);
        }
        this.rowCount = rowCount;
    }

    public SQLExpr getOffset() {
        return offset;
    }

    public void setOffset(SQLExpr offset) {
        if (offset != null) {
            offset.setParent(this);
        }
        this.offset = offset;
    }

    @Override
    protected void accept0(SQLASTVisitor visitor) {
        if (visitor.visit(this)) {
            acceptChild(visitor, offset);
            acceptChild(visitor, rowCount);
        }
        visitor.endVisit(this);
    }

}
