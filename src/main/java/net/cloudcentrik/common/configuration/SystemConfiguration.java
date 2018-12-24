package net.cloudcentrik.common.configuration;

import ch.qos.logback.classic.Logger;
import net.cloudcentrik.common.logging.AppLogger;
import net.cloudcentrik.common.util.CryptoUtils;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.FileBasedConfiguration;
import org.apache.commons.configuration2.PropertiesConfiguration;
import org.apache.commons.configuration2.builder.FileBasedConfigurationBuilder;
import org.apache.commons.configuration2.builder.fluent.Parameters;
import org.apache.commons.configuration2.ex.ConfigurationException;

import java.io.File;
import java.util.Iterator;

public class SystemConfiguration {

    private static final Logger log = AppLogger.getLogger(SystemConfiguration.class.getName());

    private static SystemConfiguration systemconfiguration;
    private static Configuration configuration;

    private SystemConfiguration(final String propertyFileName){

        final String fullFilePath=SystemConfiguration.class.getClassLoader().getResource(propertyFileName).getFile();

        Parameters params = new Parameters();
        // Read data from this file
        File propertiesFile = new File(fullFilePath);

        FileBasedConfigurationBuilder<FileBasedConfiguration> builder =
                new FileBasedConfigurationBuilder<FileBasedConfiguration>(PropertiesConfiguration.class)
                        .configure(params.fileBased()
                                .setEncoding("UTF-8")
                                .setFile(propertiesFile));
        builder.setAutoSave(true);

        try {
            configuration = builder.getConfiguration();

        } catch (ConfigurationException cex) {
            //log.error(e.getMessage());
            throw new RuntimeException("Couldnt load configuration file " + propertyFileName);
        }

    }

    public static SystemConfiguration getInstance(final String propertyFile) {
        if(systemconfiguration == null) {
            systemconfiguration = new SystemConfiguration(propertyFile);
        }
        return systemconfiguration;
    }

    public void setProperty(String key,Object value){
        Iterator<String> keys=configuration.getKeys();
        while (keys.hasNext()){
            String tempkey=keys.next();
            if(tempkey.equals(key)){
                configuration.setProperty(key,value);
                return;
            }
        }
    }

    public void addProperty(String key,Object value){
        Iterator<String> keys=configuration.getKeys();
        while (keys.hasNext()){
            String tempkey=keys.next();
            if(tempkey.equals(key)){
                configuration.setProperty(key,value);
                return;
            }
        }
        configuration.addProperty(key,value);
    }

    public void removeProperty(String key){
        configuration.clearProperty(key);
    }

    public void clearProperty(String key){
        setProperty(key,"");
    }

    public String getProperty(String key){
        return configuration.getString(key);
    }

    public boolean getBooleanProperty(String key){
        return configuration.getBoolean(key);
    }

    public double getDoubleProperty(String key){
        return configuration.getDouble(key);
    }

    public int getIntProperty(String key){
        return configuration.getInt(key);
    }

    public void setEncryptedProperty(String key,String value){
        setProperty(key,CryptoUtils.encrypt(value));
    }
    public String getDecryptedProperty(String key){
        return CryptoUtils.decrypt(getProperty(key));
    }

    public void addEncryptedProperty(String key,String value){
        Iterator<String> keys=configuration.getKeys();
        while (keys.hasNext()){
            String tempkey=keys.next();
            if(tempkey.equals(key)){
                setEncryptedProperty(key,value);
                return;
            }
        }
        configuration.addProperty(key,CryptoUtils.encrypt(value));
    }

}
