package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the NewsCommand type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "NewsCommands")
public final class NewsCommand implements Model {
  public static final QueryField ID = field("NewsCommand", "id");
  public static final QueryField DEVICE = field("NewsCommand", "Device");
  public static final QueryField COMMAND = field("NewsCommand", "Command");
  public static final QueryField NEWS_INFO = field("NewsCommand", "NewsInfo");
  public static final QueryField URL = field("NewsCommand", "Url");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String", isRequired = true) String Device;
  private final @ModelField(targetType="String") String Command;
  private final @ModelField(targetType="String") String NewsInfo;
  private final @ModelField(targetType="String") String Url;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getDevice() {
      return Device;
  }
  
  public String getCommand() {
      return Command;
  }
  
  public String getNewsInfo() {
      return NewsInfo;
  }
  
  public String getUrl() {
      return Url;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private NewsCommand(String id, String Device, String Command, String NewsInfo, String Url) {
    this.id = id;
    this.Device = Device;
    this.Command = Command;
    this.NewsInfo = NewsInfo;
    this.Url = Url;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      NewsCommand newsCommand = (NewsCommand) obj;
      return ObjectsCompat.equals(getId(), newsCommand.getId()) &&
              ObjectsCompat.equals(getDevice(), newsCommand.getDevice()) &&
              ObjectsCompat.equals(getCommand(), newsCommand.getCommand()) &&
              ObjectsCompat.equals(getNewsInfo(), newsCommand.getNewsInfo()) &&
              ObjectsCompat.equals(getUrl(), newsCommand.getUrl()) &&
              ObjectsCompat.equals(getCreatedAt(), newsCommand.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), newsCommand.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getDevice())
      .append(getCommand())
      .append(getNewsInfo())
      .append(getUrl())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("NewsCommand {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("Device=" + String.valueOf(getDevice()) + ", ")
      .append("Command=" + String.valueOf(getCommand()) + ", ")
      .append("NewsInfo=" + String.valueOf(getNewsInfo()) + ", ")
      .append("Url=" + String.valueOf(getUrl()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static DeviceStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static NewsCommand justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new NewsCommand(
      id,
      null,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      Device,
      Command,
      NewsInfo,
      Url);
  }
  public interface DeviceStep {
    BuildStep device(String device);
  }
  

  public interface BuildStep {
    NewsCommand build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep command(String command);
    BuildStep newsInfo(String newsInfo);
    BuildStep url(String url);
  }
  

  public static class Builder implements DeviceStep, BuildStep {
    private String id;
    private String Device;
    private String Command;
    private String NewsInfo;
    private String Url;
    @Override
     public NewsCommand build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new NewsCommand(
          id,
          Device,
          Command,
          NewsInfo,
          Url);
    }
    
    @Override
     public BuildStep device(String device) {
        Objects.requireNonNull(device);
        this.Device = device;
        return this;
    }
    
    @Override
     public BuildStep command(String command) {
        this.Command = command;
        return this;
    }
    
    @Override
     public BuildStep newsInfo(String newsInfo) {
        this.NewsInfo = newsInfo;
        return this;
    }
    
    @Override
     public BuildStep url(String url) {
        this.Url = url;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String device, String command, String newsInfo, String url) {
      super.id(id);
      super.device(device)
        .command(command)
        .newsInfo(newsInfo)
        .url(url);
    }
    
    @Override
     public CopyOfBuilder device(String device) {
      return (CopyOfBuilder) super.device(device);
    }
    
    @Override
     public CopyOfBuilder command(String command) {
      return (CopyOfBuilder) super.command(command);
    }
    
    @Override
     public CopyOfBuilder newsInfo(String newsInfo) {
      return (CopyOfBuilder) super.newsInfo(newsInfo);
    }
    
    @Override
     public CopyOfBuilder url(String url) {
      return (CopyOfBuilder) super.url(url);
    }
  }
  
}
