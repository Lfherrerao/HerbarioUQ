package util;

import java.util.Collections;
import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter(value="selectItemObjectConverter",managed=true)
public class SelectItemObjectConvert implements Converter {
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		final int index = Integer.parseInt(value);
		if (index == -1) {
			return null;
		}
		final List<?> objects = getItemsObjects(component);
		return objects.get(index);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		final List<?> objects = getItemsObjects(component);
		return String.valueOf(objects.indexOf(value));
	}

	private List<?> getItemsObjects(UIComponent component) {
		List<?> objects = Collections.emptyList();
		for (UIComponent child : component.getChildren()) {
			if (child.getClass() == UISelectItems.class) {
				objects = (List<?>) ((UISelectItems) child).getValue();
			}
		}
		return objects;
	}
}
