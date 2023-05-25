package HitListener;

/**
 * Name: Bar Ben Hamou.<br>
 * id number: 330591207.<br>
 * Notifier.
 */
public interface HitNotifier {

    /**
     * Add h1 as a new hit listener.<br>
     *
     * @param h1 the new hit listener.
     */
    void addHitListener(HitListener h1);

    /**
     * Remove h1 as a hit listener.<br>
     *
     * @param h1 the wanted hit listener.
     */
    void removeHitListener(HitListener h1);
}