package slaythestarcraft2mod.cards.protoss;

import java.util.Iterator;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;

import slaythestarcraft2mod.SlaytheStarCraft2Mod;
import slaythestarcraft2mod.abstracts.OverLoadCustomCard;
import slaythestarcraft2mod.patches.AbstractCardEnum;
import slaythestarcraft2mod.initializers.ImgInitializer;

public class RageofHighKing extends OverLoadCustomCard{

	
	public static final String ID = SlaytheStarCraft2Mod.makeID("RageofHighKing");
	private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
	
	public static final String IMG = ImgInitializer.makePath(ImgInitializer.RAGEOFHIGHKING);
	
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	
	private static final CardRarity RARITY = CardRarity.RARE;
	private static final CardTarget TARGET = CardTarget.SELF;
	private static final CardType TYPE = CardType.SKILL;
	public static final CardColor COLOR = AbstractCardEnum.PROTOSS_BLUE;
	
	private static final int COST = 1;
	
	private static final int MAGIC_NUMBER = 1;
	private static final int UPGRADE_PLUS_MAGIC_NUMBER = 1;
	private static final int OVERLOAD = 2;
	
	public RageofHighKing() {
		super(ID, NAME, IMG, COST, DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
		this.baseMagicNumber=this.magicNumber=MAGIC_NUMBER;
		this.overLoad=OVERLOAD;
	}
	
	@Override
	public void use(AbstractPlayer p,AbstractMonster m) {
		int count = 0;
		Iterator<AbstractMonster> var4 = AbstractDungeon.getMonsters().monsters.iterator();

		while (var4.hasNext()) {
			AbstractMonster mon = (AbstractMonster) var4.next();
			if (!mon.isDeadOrEscaped()) {
				++count;
			}
		}
		AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, count*this.magicNumber), count*this.magicNumber));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new RageofHighKing();
	}
	
	@Override
	public void upgrade() {
		if(!this.upgraded) {
			this.upgradeName();
			this.upgradeMagicNumber(UPGRADE_PLUS_MAGIC_NUMBER);
			this.initializeDescription();
		}
	}
	
}

