package org.h2.value;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.h2.api.ErrorCode;
import org.h2.message.DbException;
import org.h2.util.StringUtils;

public class ValuePhoneNumber extends Value {
	
	public static final ValuePhoneNumber EMPTY = new ValuePhoneNumber("");
	
	public static final int PRECISION = 10;
	
	private final String phoneNumberValue;
	
	private ValuePhoneNumber(String phoneNumberValue) {
		if(isValid(phoneNumberValue))
			this.phoneNumberValue = phoneNumberValue;
		else{
			throw DbException.get(ErrorCode.UNKNOWN_DATA_TYPE_1);
		}
    }
	
	@Override
	public String getSQL() {
		return StringUtils.quoteStringSQL(phoneNumberValue);
	}

	@Override
	public int getType() {
		return Value.PHONE_NUMBER;
	}

	@Override
	public long getPrecision() {
		return PRECISION;
	}

	@Override
	public int getDisplaySize() {
		return PRECISION;
	}

	@Override
	public String getString() {
		return phoneNumberValue.toString();
	}

	@Override
	public Object getObject() {
		return EMPTY;
	}

	@Override
	public void set(PreparedStatement prep, int parameterIndex) throws SQLException {
		prep.setString(parameterIndex, phoneNumberValue);
		
	}

	@Override
	public int hashCode() {
		return phoneNumberValue.hashCode();
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof ValuePhoneNumber
                && phoneNumberValue.equals(((ValuePhoneNumber) other).phoneNumberValue);
	}

	@Override
	public int compareTypeSafe(Value v, CompareMode mode) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	private boolean isValid(String number){
		String regex = "\\d{10}";
		Pattern pr = Pattern.compile(regex);
		Matcher match = pr.matcher(regex);
		return match.matches();
	}
	
}

