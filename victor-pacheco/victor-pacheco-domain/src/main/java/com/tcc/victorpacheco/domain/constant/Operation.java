package com.tcc.victorpacheco.domain.constant;

public enum Operation {

    SEARCH(0),
    CREATE(1),
    UPDATE(2),
    DELETE(3);

    private final Integer operationId;

    Operation(Integer operationId){
        this.operationId = operationId;
    }

    public Integer getOperationId() {
        return operationId;
    }
}
