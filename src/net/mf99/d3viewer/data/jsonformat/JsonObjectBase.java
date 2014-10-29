package net.mf99.d3viewer.data.jsonformat;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import android.util.Log;

public class JsonObjectBase {
	
	public JsonObjectBase(JSONObject jsonData){
		try {
			this.parseJsonFormat(jsonData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void parseJsonFormat(JSONObject jsonData) throws Exception{
		if(jsonData==null)
			return;
		
		for(Field field : getClass().getDeclaredFields()){
			field.setAccessible(true);				
			String fieldName=field.getName();
			
			if(fieldName.startsWith("_"))
				fieldName = fieldName.substring("_".length());
			
			boolean hasField = jsonData.has(fieldName);
			
			if(hasField && (field.getType().getName().equals(Integer.class.getName()) || field.getType().getName().equals(int.class.getName()))){
				field.setInt(this, jsonData.getInt(fieldName));
			}else if(hasField && (field.getType().getName().equals(Double.class.getName())||field.getType().getName().equals(double.class.getName()))){
				field.setDouble(this, jsonData.getDouble(fieldName));
			}else if(hasField && (field.getType().getName().equals(Long.class.getName())||field.getType().getName().equals(long.class.getName()))){
				field.setLong(this, jsonData.getLong(fieldName));
			}else if(hasField && (field.getType().getName().equals(Boolean.class.getName())||field.getType().getName().equals(boolean.class.getName()))){
				field.setBoolean(this, jsonData.getBoolean(fieldName));
			}else if(hasField && field.getType().getName().equals(String.class.getName())){//					
				field.set(this, jsonData.getString(fieldName));
			}else if(hasField && (field.getType().getSuperclass())!=null && field.getType().getSuperclass().getName().equals(JsonObjectBase.class.getName())){					
				JSONObject subJSONFormat=(JSONObject) jsonData.get(fieldName);
				Class<?> myClass = Class.forName(field.getType().getName());
				Constructor<?>  mConstructor=myClass.getConstructor(JSONObject.class);
				if(mConstructor!=null){
					Object retriveObject = null;
					retriveObject = mConstructor.newInstance(subJSONFormat);
					field.set(this, retriveObject);
				}
			}
			
			else if(hasField && field.getType().getName().equals(List.class.getName())){				
				Method getterMethod=null;
				for(Method method:this.getClass().getDeclaredMethods()){						
					if(method.getName().equalsIgnoreCase("get"+field.getName())){
						getterMethod=method;
						getterMethod.setAccessible(true);
						break;
					}
				}
				
				List<Object> listObjects=null;
				if(getterMethod!=null)
					listObjects=(List<Object>)getterMethod.invoke(this);
				
				String className=((ParameterizedType)field.getGenericType()).getActualTypeArguments()[0].toString().split(" ")[1];
				JSONArray mJSONArray=jsonData.getJSONArray(fieldName);	
				Constructor<?> mConstructor=null;
				Class<?> myClass = Class.forName(className);				

				if(className!=null){
					for(int i=0;i<mJSONArray.length();i++){							
						JSONObject innerJSONData=(JSONObject) mJSONArray.get(i);
						mConstructor = myClass.getConstructor(JSONObject.class);
						if(mConstructor!=null){
							Object retriveObject = null;
							retriveObject = mConstructor.newInstance(innerJSONData);
							
							if(listObjects!=null)
								listObjects.add(retriveObject);
						}
							
					}
				}
			}
		}
	}

}
