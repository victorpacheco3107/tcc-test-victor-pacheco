package com.tcc.victorpacheco.domain.constant;

/**
 * Enum that represents genders.
 * @author <a href="mailto:victorpacheco3107@gmail.com">Victor Uriel Pacheco Castro</a>
 * @since 1.0
 */
public enum Operation {

    /**
     * Constant for search a client.
     */
    SEARCH(0),
    /**
     * Constant for create a client.
     */
    CREATE(1),
    /**
     * Constant for update a client.
     */
    UPDATE(2),
    /**
     * Constant for delete a client.
     */
    DELETE(3);

    /**
     * Operation id that the implementation must keep in mind.
     */
    private final Integer operationId;

    Operation(Integer operationId){
        this.operationId = operationId;
    }

    public Integer getOperationId() {
        return operationId;
    }
}
