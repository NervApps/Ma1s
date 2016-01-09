/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.nerv.eva.service.validation;

import br.com.ma1s.eva.model.Property;
import br.com.ma1s.eva.model.PropertyCustomer;
import br.com.nerv.eva.exception.BusinessException;
import java.math.BigDecimal;

/**
 *
 * @author Vitor
 */
class RentPropertyValidation extends PropertyValidation {
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
        final BigDecimal months = new BigDecimal(pc.getMonths());
        final Property p = pc.getProperty();
        final int compare = pc.getDepositValue()
                              .compareTo(p.getValue()
                              .multiply(months));
        
        if (compare == COMPARE_LESS)
            throw new BusinessException("O valor do depósito não pode ser"
                    + " menor que três vezes o valor do aluguel");
    }
}