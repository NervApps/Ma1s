/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ma1s.eva.service.validation;

import br.com.ma1s.eva.exception.BusinessException;
import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.PropertyCustomer;
import java.math.BigDecimal;

/**
 *
 * @author Vitor
 */
class RentPropertyValidation extends PropertyValidation {
    private static final BigDecimal DEPOSIT_MONTHS = new BigDecimal(3);
    private static final int COMPARE_LESS = -1;

    public RentPropertyValidation(PropertyCustomer pc) {
        super(pc);
    }

    @Override
    public void validate() throws BusinessException {
        super.validate();
        validateDeposit();
    }
    
    private void validateDeposit() {
        final Property p = pc.getProperty();
        final int compare = pc.getDepositValue()
                              .compareTo(p.getValue().multiply(DEPOSIT_MONTHS));
        
        if (compare == COMPARE_LESS)
            throw new BusinessException("O valor do dep�sito n�o pode ser"
                    + " menor que tr�s vezes o valor do aluguel");
    }
}