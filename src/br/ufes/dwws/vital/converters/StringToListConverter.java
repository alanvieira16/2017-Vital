package br.ufes.dwws.vital.converters;

import java.util.ArrayList;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="str2listConverter")
public class StringToListConverter implements Converter
{
    @Override
    public Object getAsObject(final FacesContext context, final UIComponent component, final String values)
    {
        List<String> result = new ArrayList<String>(); 
        for (String value : values.split(",", -1))
        {           
            final String trimmedValue = value.trim();
            result.add(new String(trimmedValue));
        }       
        return result;
    }

    @Override
    public String getAsString(final FacesContext context, final UIComponent component, final Object value)
    {
        if (value instanceof List<?>)
        {
            final StringBuffer result = new StringBuffer();
            final List<?> list = (List<?>) value;
            for (int i = 0; i < list.size()-1; i++)
            {               
                if (list.get(i) instanceof String)
                {
                    result.append(((String) list.get(i)));
                    result.append(",");
                }
            }

            if (!list.isEmpty())
            {
                if (list.get(list.size()-1) instanceof String)
                {
                    result.append(((String) list.get(list.size()-1)));
                }
                else
                {
                    throw new IllegalArgumentException( "Cannot convert " + value + " object to String." );
                }
            }

            return result.toString();
        }
        else
        {
            throw new IllegalArgumentException( "Cannot convert " + value + " object to List." );
        }
    }
}