package hotelmanagement.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class RoomKey {

  private final String keyId;
  private final String barCode;
  private final boolean isMaster;
  private final String issuedBy;
  private LocalDateTime issuedAt;
  private boolean isActive;

  public RoomKey(String barCode, boolean isActive, boolean isMaster, LocalDateTime issuedAt, String issuedBy) {
    this.keyId = "K#" + UUID.randomUUID();
    this.barCode = barCode;
    this.isActive = isActive;
    this.isMaster = isMaster;
    this.issuedAt = issuedAt;
    this.issuedBy = issuedBy;
  }

  public String getKeyId() {
    return keyId;
  }

  public String getBarCode() {
    return barCode;
  }

  public boolean isMaster() {
    return isMaster;
  }

  public LocalDateTime getIssuedAt() {
    return issuedAt;
  }

  public void setIssuedAt(LocalDateTime issuedAt) {
    this.issuedAt = issuedAt;
  }

  public String getIssuedBy() {
    return issuedBy;
  }

  public boolean isActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }
}
