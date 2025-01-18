package computer.heather.simpleconfig.managers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import computer.heather.simpleconfig.exceptions.validation.BaseValidationException;
import computer.heather.simpleconfig.types.BaseConfigType;
import computer.heather.simpleconfig.types.FakeConfigType;
import computer.heather.simpleconfig.util.ValidationErrorHandler;


/**
 * A {@link IConfigManager} designed to use a "premade" properties file, rather than building the full file at runtime.
 */
public class PremadePropertiesManager implements IConfigManager {

    private HashMap<String, BaseConfigType<?>> entries = new HashMap<>();
    private Path configLocation;

    /**
     * The classpath location to get the premade properties file from.
     * By default, this is just the filename of the config location, but there is a setter below.
     */
    private String premadeLocation = "";


    @Override
    public IConfigManager register(BaseConfigType<?> entry) {
        this.entries.put(entry.getKey(), entry);
        return this;
    }

    @Override
    public IConfigManager setConfigLocation(Path location) {
        this.configLocation = location;
        if (this.premadeLocation == "") {
            this.premadeLocation = this.configLocation.getFileName().toString();
        }
        return this;
    }

    public IConfigManager setPremadeLocation(String location) {
        this.premadeLocation = location;
        return this;
    }

    @Override
    public void load(ValidationErrorHandler<BaseConfigType<?>, String> errorHandler)
            throws IOException, FileNotFoundException, BaseValidationException {

        Properties props = new Properties();
        FileReader reader = new FileReader(configLocation.toFile());
        
        props.load(reader);
        reader.close();

        ArrayList<String> erroringKeys = new ArrayList<>();

        for (String key : entries.keySet()) {
            //This attempts validation before any loading. 
            try {
                entries.get(key).validate(props.getProperty(key));
            } catch (BaseValidationException e) {
                errorHandler.accept(entries.get(key), props.getProperty(key));
                //At this point, the error handler would've "handled" the error.
                //Add it to our erroringKeys arraylist so we know to skip loading - thus keeping the config option at default or whatever the errorhandler specifies.
                erroringKeys.add(key);
            }
        }

        //Call errorHandler for every properties key that isn't registered as a config entry.
        //Allows for "migration" code.

        for (Object keyOjb : props.keySet()) {
            //We know this is a string...
            String key = (String) keyOjb;
            if (entries.containsKey(key)) continue;
            //Create a fake config entry just as it allows the error handler to access the detected key and migrate accordingly.
            errorHandler.accept(new FakeConfigType(key), props.getProperty(key));
            //Add it to our erroringKeys arraylist just to indicate that we've ran the errorhandler.
            erroringKeys.add(key);
        }

        //Now, we know nothing will error because everything has validated, migration code has ran, etc.
        for (String key : entries.keySet()) {
            //Actually load.
            //Skip this key as it failed to validate and the error handler handled it.
            if (erroringKeys.contains(key)) continue;
            entries.get(key).load(props.getProperty(key));
        }

        //Finally, end things off with a save if the errorhandler was invoked at all.
        //This ensures anything it did gets saved.
        if (!erroringKeys.isEmpty()) save();

    }

    @Override
    public void save() throws IOException {
        File file = configLocation.toFile();

        //Creates the file if it doesn't exist.
        file.createNewFile();
        file.setWritable(true);

        String premadeText;
        //Read the premade properties file.
        InputStream is = PremadePropertiesManager.class.getClassLoader().getResourceAsStream(premadeLocation);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
        premadeText = reader.lines().collect(Collectors.joining("\n"));
        reader.close();

        //Save each existing config value to the loaded premade text.
        for (String key : entries.keySet()) {
            Matcher matcher = Pattern.compile(Pattern.quote(key) + "$", Pattern.MULTILINE).matcher(premadeText);
            premadeText = matcher.replaceAll(key + "=" + entries.get(key).save());
        }

        //Write the file to disk.
        FileWriter writer = new FileWriter(file);
        writer.write(premadeText);
        writer.close();

    }

}
